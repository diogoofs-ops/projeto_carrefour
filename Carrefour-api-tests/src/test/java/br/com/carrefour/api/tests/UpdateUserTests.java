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

public class UpdateUserTests {

    @Test
    public void testUpdateUserIfNeeded() throws IOException {
        // 1. Buscar todos os usuários
        Response response = given()
                .when()
                .get("https://serverest.dev/usuarios");

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, String>> usuarios = jsonPath.getList("usuarios");

        // 2. Procurar o usuário "Diogo Silva1"
        Map<String, String> usuarioAlvo = null;
        for (Map<String, String> usuario : usuarios) {
            if ("Diogo Silva1".equals(usuario.get("nome"))) {
                usuarioAlvo = usuario;
                break;
            }
        }

        // 3. Validar se encontrou o usuário
        assertNotNull(usuarioAlvo, "❌ Usuário 'Diogo Silva1' não encontrado.");

        String userId = usuarioAlvo.get("_id");

        // 4. Dados desejados para atualização
        String novoNome = "Diogo Silva1";
        String novoEmail = "diogo.silva1@carrefour.com";
        String novaSenha = "senhaAtualizada1234";
        String novoAdmin = "true";

        // 5. Verificar se os dados já estão atualizados
        boolean jaAtualizado = novoNome.equals(usuarioAlvo.get("nome")) &&
                novoEmail.equals(usuarioAlvo.get("email")) &&
                novaSenha.equals(usuarioAlvo.get("password")) &&
                novoAdmin.equals(usuarioAlvo.get("administrador"));

        if (jaAtualizado) {
            assertTrue(jaAtualizado, "ℹ️ O usuário já está com os dados atualizados. Nenhuma ação necessária.");
        } else {
            // 6. Enviar PUT para atualizar
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

            // ✅ 7. Salvar resposta da API em arquivo
            Path caminhoLog = Paths.get("logs/resultado.txt");
            Files.createDirectories(caminhoLog.getParent());
            Files.writeString(caminhoLog, updateResponse.asString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // 8. Validar conteúdo do log
            String conteudoArquivo = Files.readString(caminhoLog);
            assertEquals(updateResponse.asString(), conteudoArquivo, "O conteúdo do log deve ser igual à resposta da API");

            // 9. Verificar resultado
            assertEquals(200, statusCode, "Status esperado: 200");
            assertTrue(mensagem.contains("Registro alterado com sucesso"), "Mensagem esperada na resposta");
        }
    }
}