package com.example.webapp.controller;

import com.example.webapp.model.User;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Integration tests for UserRestController using REST Assured.
 * Tests the REST API endpoints.
 */
@QuarkusTest
class UserRestControllerTest {

    @Test
    void testGetAllUsersEndpoint() {
        given()
            .when()
            .get("/api/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test
    void testCreateAndGetUser() {
        User user = new User("test_user", "test@example.com");

        // Create user
        given()
            .contentType(ContentType.JSON)
            .body(user)
            .when()
            .post("/api/users")
            .then()
            .statusCode(201)
            .body("username", equalTo("test_user"))
            .body("email", equalTo("test@example.com"));
    }

    @Test
    void testGetUserByIdNotFound() {
        given()
            .when()
            .get("/api/users/99999")
            .then()
            .statusCode(404);
    }

    @Test
    void testDeleteUserNotFound() {
        given()
            .when()
            .delete("/api/users/99999")
            .then()
            .statusCode(404);
    }
}
