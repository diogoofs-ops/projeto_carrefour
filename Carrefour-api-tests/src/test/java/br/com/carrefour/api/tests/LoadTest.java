package br.com.carrefour.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class LoadTest {

    private final Path caminhoLog = Paths.get("logs/resultado.txt");
    private final Object lock = new Object();

    @Test
    public void test200RequestsInParallel() throws InterruptedException, IOException {
        int totalRequests = 200;
        int threadPoolSize = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        CountDownLatch latch = new CountDownLatch(totalRequests);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        // ✅ Preparar arquivo de log
        Files.createDirectories(caminhoLog.getParent());
        Files.writeString(caminhoLog, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        long start = System.currentTimeMillis();

        for (int i = 0; i < totalRequests; i++) {
            executor.submit(() -> {
                try {
                    Response response = RestAssured
                            .given()
                            .when()
                            .get("https://serverest.dev/usuarios");

                    int statusCode = response.getStatusCode();
                    if (statusCode == 200) {
                        successCount.incrementAndGet();
                    } else {
                        failureCount.incrementAndGet();
                    }

                    String log = "Status: " + statusCode +
                            " | Tempo: " + response.time() + " ms\n" +
                            response.asString() + "\n\n";

                    synchronized (lock) {
                        Files.writeString(caminhoLog, log, StandardOpenOption.APPEND);
                    }

                } catch (Exception e) {
                    failureCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        long end = System.currentTimeMillis();
        executor.shutdown();

        // ✅ Validações
        assertEquals(0, failureCount.get(), "Todas as requisições devem retornar sucesso");
        assertEquals(totalRequests, successCount.get(), "Número de respostas bem-sucedidas deve ser igual ao total de requisições");

        // ✅ Validar que o log foi salvo
        String conteudoLog = Files.readString(caminhoLog);
        assertTrue(conteudoLog.contains("Status: 200"), "O log deve conter respostas com status 200");

        // ✅ Validar tempo total (opcional)
        long tempoTotal = end - start;
        assertTrue(tempoTotal < 10000, "Tempo total deve ser inferior a 10 segundos (ajustável)");
    }
}