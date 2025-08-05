package br.com.carrefour.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetUserByIdTest {

    @Test
    public void testGetUserById() {
        // 1. Buscar o ID do primeiro usuário
        String userId =
                given()
                        .log().all()
                        .when()
                        .get("https://serverest.dev/usuarios")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .path("usuarios[0]._id");

        // 2. Buscar o usuário por ID e extrair a resposta
        Response response = given()
                .log().all()
                .pathParam("id", userId)
                .when()
                .get("https://serverest.dev/usuarios/{id}");

        // 3. Validar o conteúdo da resposta
        response.then()
                .statusCode(200)
                .body("_id", equalTo(userId));

        // ✅ 4. Salvar resposta da API em arquivo
        Path caminhoLog = Paths.get("logs/resultado.txt");
        try {
            Files.createDirectories(caminhoLog.getParent());
            Files.writeString(caminhoLog, response.asString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // 5. Validar que o conteúdo do arquivo é igual à resposta
            String conteudoArquivo = Files.readString(caminhoLog);
            assertEquals(response.asString(), conteudoArquivo, "O conteúdo do log deve ser igual à resposta da API");

        } catch (IOException e) {
            fail("❌ Erro ao salvar ou ler o log: " + e.getMessage());
        }
    }
}