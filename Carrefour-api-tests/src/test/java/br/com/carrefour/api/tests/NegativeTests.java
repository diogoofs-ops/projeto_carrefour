package br.com.carrefour.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class NegativeTests {

    @Test
    public void testInvalidLogin() {
        // 1. Enviar requisição e capturar resposta
        Response response = given()
                .body("{\"email\":\"invalido@teste.com\",\"password\":\"1234\"}")
                .when()
                .post("https://serverest.dev/login");

        // 2. Validar status esperado
        assertEquals(400, response.getStatusCode(), "Deve retornar status 400 para login inválido");

        // 3. Validar mensagem de erro
        String responseBody = response.asString();
        assertTrue(responseBody.contains("email ou senha inválidos") || responseBody.contains("erro"), "Mensagem de erro esperada na resposta");

        // ✅ 4. Salvar resposta da API em arquivo
        Path caminhoLog = Paths.get("logs/resultado.txt");
        try {
            Files.createDirectories(caminhoLog.getParent());
            Files.writeString(caminhoLog, responseBody, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // 5. Validar que o conteúdo do log é igual à resposta
            String conteudoArquivo = Files.readString(caminhoLog);
            assertEquals(responseBody, conteudoArquivo, "O conteúdo do log deve ser igual à resposta da API");

        } catch (IOException e) {
            fail("❌ Erro ao salvar ou ler o log: " + e.getMessage());
        }
    }
}