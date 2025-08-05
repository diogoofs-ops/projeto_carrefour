package br.com.carrefour.api.tests;

import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    @Test
    public void testGetUsers() throws IOException {
        // 1. Fazer requisição e capturar resposta
        Response response = given()
                .log().all()
                .when()
                .get("https://serverest.dev/usuarios");

        // 2. Validar resposta
        response.then()
                .log().body()
                .statusCode(200)
                .body("usuarios", notNullValue());

        // 3. Salvar resposta em arquivo
        Path caminhoLog = Paths.get("logs/resultado.txt");
        Files.createDirectories(caminhoLog.getParent());
        Files.writeString(caminhoLog, response.asString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // 4. Validar que o conteúdo do arquivo é igual à resposta
        String conteudoArquivo = Files.readString(caminhoLog);
        assertEquals(response.asString(), conteudoArquivo, "O conteúdo do log deve ser igual à resposta da API");
    }
}
