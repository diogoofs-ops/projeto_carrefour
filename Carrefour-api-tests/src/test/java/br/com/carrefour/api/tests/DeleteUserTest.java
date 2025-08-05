package br.com.carrefour.api.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteUserTest {

    @Test
    public void testDeleteUserDiogoFerrera() throws IOException {
        // 1. Buscar todos os usuários
        Response getResponse = given()
                .when()
                .get("https://serverest.dev/usuarios");

        JsonPath jsonPath = getResponse.jsonPath();
        List<Map<String, String>> usuarios = jsonPath.getList("usuarios");

        // 2. Procurar o ID do usuário com nome "Diogo Ferrera"
        String userId = null;
        for (Map<String, String> usuario : usuarios) {
            if ("Diogo Silva1".equals(usuario.get("nome"))) {
                userId = usuario.get("_id");
                break;
            }
        }

        // 3. Validar se encontrou o usuário
        assertNotNull(userId, "❌ Usuário 'Diogo Silva1' não encontrado.");

        // 4. Enviar requisição DELETE
        Response deleteResponse = given()
                .pathParam("id", userId)
                .when()
                .delete("https://serverest.dev/usuarios/{id}");

        int statusCode = deleteResponse.getStatusCode();
        String responseBody = deleteResponse.getBody().asString();

        // 5. Salvar resposta em arquivo
        Path caminhoLog = Paths.get("logs/resultado.txt");
        Files.createDirectories(caminhoLog.getParent());
        Files.writeString(caminhoLog, responseBody, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // 6. Validar conteúdo do log
        String conteudoArquivo = Files.readString(caminhoLog);
        assertEquals(responseBody, conteudoArquivo, "O conteúdo do log deve ser igual à resposta da API");

        // 7. Validar resposta da API
        if (statusCode == 200) {
            assertTrue(responseBody.contains("Registro excluído com sucesso"), "Mensagem de sucesso esperada");
        } else if (statusCode == 400) {
            assertTrue(responseBody.contains("Não é permitido excluir usuário que possui carrinho"), "Mensagem de erro esperada");
        } else {
            fail("❌ Status inesperado: " + statusCode + "\nResposta: " + responseBody);
        }
    }
}