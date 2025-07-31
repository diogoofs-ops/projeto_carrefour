package br.com.carrefour.api.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class UpdateUserTests {

    @Test
    public void testUpdateUserIfNeeded() {
        // 1. Buscar todos os usuários
        Response response = given()
                .when()
                .get("https://serverest.dev/usuarios");

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, String>> usuarios = jsonPath.getList("usuarios");

        // 2. Procurar o usuário "Diogo Ferrera"
        Map<String, String> usuarioAlvo = null;
        for (Map<String, String> usuario : usuarios) {
            if ("Diogo Atualizado".equals(usuario.get("nome"))) {
                usuarioAlvo = usuario;
                break;
            }
        }

        if (usuarioAlvo == null) {
            System.out.println("❌ Usuário 'Diogo Atualizado' não encontrado.");
            return;
        }

        String userId = usuarioAlvo.get("_id");

        // 3. Dados desejados para atualização
        String novoNome = "Diogo Atualizado";
        String novoEmail = "diogo.atualizado@carrefour.com";
        String novaSenha = "senhaAtualizada123";
        String novoAdmin = "true";

        // 4. Verificar se os dados já estão atualizados
        boolean jaAtualizado = novoNome.equals(usuarioAlvo.get("nome")) &&
                novoEmail.equals(usuarioAlvo.get("email")) &&
                novaSenha.equals(usuarioAlvo.get("password")) &&
                novoAdmin.equals(usuarioAlvo.get("administrador"));

        if (jaAtualizado) {
            System.out.println("ℹ️ O usuário já está com os dados atualizados. Nenhuma ação necessária.");
        } else {
            // 5. Enviar PUT para atualizar
            String updatedData = "{\n" +
                    "  \"nome\": \"" + novoNome + "\",\n" +
                    "  \"email\": \"" + novoEmail + "\",\n" +
                    "  \"password\": \"" + novaSenha + "\",\n" +
                    "  \"administrador\": \"" + novoAdmin + "\"\n" +
                    "}";

            Response updateResponse = given()
                    .contentType("application/json")
                    .body(updatedData)
                    .when()
                    .put("https://serverest.dev/usuarios/" + userId);

            String mensagem = updateResponse.jsonPath().getString("message");
            int statusCode = updateResponse.getStatusCode();

            if (statusCode == 200 && mensagem.contains("Registro alterado com sucesso")) {
                System.out.println("✅ Usuário atualizado com sucesso!");
            } else {
                System.out.println("❌ Falha ao atualizar. Status: " + statusCode);
                System.out.println("Mensagem: " + mensagem);
            }
        }
    }
}
