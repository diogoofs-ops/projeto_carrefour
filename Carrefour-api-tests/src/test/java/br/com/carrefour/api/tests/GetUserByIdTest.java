package br.com.carrefour.api.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserByIdTest {

    @Test
    public void testGetUserById() {

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


        given()
                .log().all()
                .pathParam("id", userId)
                .when()
                .get("https://serverest.dev/usuarios/{id}")
                .then()
                .statusCode(200)
                .body("_id", equalTo(userId));
        System.out.println("O id: " + userId + " refere-se ao: Fulano da Silva!" );
    }
}
