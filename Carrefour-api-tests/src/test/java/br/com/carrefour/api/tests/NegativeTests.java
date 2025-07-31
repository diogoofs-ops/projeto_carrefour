package br.com.carrefour.api.tests;

import org.junit.jupiter.api.Test;

import java.lang.constant.DynamicCallSiteDesc;

import static io.restassured.RestAssured.*;

public class NegativeTests {
    @Test
    public void testInvalidLogin() {
        given()
                .body("{\"email\":\"invalido@teste.com\",\"password\":\"1234\"}")
                  .when().post("https://serverest.dev/login")
                 .then()
                .statusCode(400);
        System.out.println("Falha ao fazer login com credenciais inválidas, código de status 400 retornado.");
    }
}