package br.com.carrefour.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTests {

    @Test
    public void testGetUsers() throws IOException {

        String bodyRequest = "{\n" +
                "  \"nome\": \"Diogo Silva1\",\n" +
                "  \"email\": \"diogo.silva1@carrefour.com\",\n" +
                "  \"password\": \"senhaSegura123\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(bodyRequest)
                .when()
                .post("https://serverest.dev/usuarios");

        // ✅ Salvar resposta da API em arquivo
        Path caminhoLog = Paths.get("logs/resultado.txt");
        Files.createDirectories(caminhoLog.getParent());
        Files.writeString(caminhoLog, response.asString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // ✅ Validar que o conteúdo do arquivo é igual à resposta
        String conteudoArquivo = Files.readString(caminhoLog);
        assertEquals(response.asString(), conteudoArquivo, "O conteúdo do log deve ser igual à resposta da API");

        // ✅ Validação da resposta
        int statusCode = response.statusCode();
        String responseBody = response.body().asString();

        if (statusCode == 201) {
            assertEquals(201, statusCode, "Usuário deve ser criado com sucesso");
        } else if (statusCode == 400) {
            assertTrue(responseBody.contains("email já está sendo usado"), "Erro esperado: email já está sendo usado");
        } else {
            fail("Status inesperado: " + statusCode + "\nResposta: " + responseBody);
        }
    }
}