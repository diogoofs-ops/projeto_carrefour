package br.com.carrefour.api.tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

public class UserTests {
    @Test
    public void testGetUsers() {
        given()
                .log().all()
                .when()
                .get("https://serverest.dev/usuarios")
                .then()
                .log().body()
                .statusCode(200)
                .body("usuarios", notNullValue());
    }
}
