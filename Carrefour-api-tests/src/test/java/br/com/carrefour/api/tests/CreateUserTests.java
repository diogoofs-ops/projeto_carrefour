package br.com.carrefour.api.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTests {

    @Test
    public void testGetUsers() {

        String bodyRequest = "{\n" +
                "  \"nome\": \"Diogo Ferrera\",\n" +
                "  \"email\": \"diogo.ferrera@carrefour.com\",\n" +
                "  \"password\": \"senhaSegura12\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(bodyRequest)
                .when()
                .post("https://serverest.dev/usuarios");

        if (response.statusCode() == 201) {
            System.out.println("✅ O Usuário foi criado com sucesso!");
        } else if (response.statusCode() == 400 && response.body().asString().contains("email já está sendo usado")) {
            System.out.println("⚠️ Erro: O usuário já foi criado anteriormente.");
        } else {
            System.out.println("❌ Erro ao criar usuário. Status: " + response.statusCode());
            System.out.println("Resposta da API: " + response.body().asString());
        }
    }
}
