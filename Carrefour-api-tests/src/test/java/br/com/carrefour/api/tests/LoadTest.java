package br.com.carrefour.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class LoadTest {

    @Test
    public void test200RequestsInParallel() throws InterruptedException {
        int totalRequests = 200;
        int threadPoolSize = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        CountDownLatch latch = new CountDownLatch(totalRequests);

        long start = System.currentTimeMillis();

        for (int i = 0; i < totalRequests; i++) {
            executor.submit(() -> {
                try {
                    Response response = RestAssured
                            .given()
                            .when()
                            .get("https://serverest.dev/usuarios");

                    System.out.println("Status: " + response.getStatusCode() +
                            " | Tempo: " + response.time() + " ms");
                } catch (Exception e) {
                    System.err.println("Erro na requisição: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Aguarda todas as requisições terminarem
        long end = System.currentTimeMillis();

        System.out.println("✅ Todas as " + totalRequests + " requisições foram concluídas.");
        System.out.println("⏱️ Tempo total: " + (end - start) + " ms");

        executor.shutdown();
    }
}
