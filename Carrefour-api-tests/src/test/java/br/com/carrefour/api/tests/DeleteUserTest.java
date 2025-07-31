package br.com.carrefour.api.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class DeleteUserTest {

    @Test
    public void testDeleteUserDiogoFerrera() {
        // 1. Buscar todos os usuários
        Response getResponse = given()
                .when()
                .get("https://serverest.dev/usuarios");

        JsonPath jsonPath = getResponse.jsonPath();
        List<Map<String, String>> usuarios = jsonPath.getList("usuarios");

        // 2. Procurar o ID do usuário com nome "Diogo Ferrera"
        String userId = null;
        for (Map<String, String> usuario : usuarios) {
            if ("Diogo Ferrera".equals(usuario.get("nome"))) {
                userId = usuario.get("_id");
                break;
            }
        }

        // 3. Validar se encontrou o usuário
        if (userId == null) {
            System.out.println("❌ Usuário 'Diogo Ferrera' não encontrado.");
            return;
        }

        // 4. Enviar requisição DELETE
        Response deleteResponse = given()
                .pathParam("id", userId)
                .when()
                .delete("https://serverest.dev/usuarios/{id}");

        int statusCode = deleteResponse.getStatusCode();
        String responseBody = deleteResponse.getBody().asString();

        // 5. Verificar resultado
        if (statusCode == 200) {
            System.out.println("✅ Usuário deletado com sucesso!");
        } else if (statusCode == 400) {
            System.out.println("⚠️ Não foi possível deletar o usuário. Ele pode já ter sido excluído ou não existir.");
            System.out.println("Resposta da API: " + responseBody);
        } else {
            System.out.println("❌ Erro inesperado ao tentar deletar o usuário. Status: " + statusCode);
            System.out.println("Resposta da API: " + responseBody);
        }
    }
}
