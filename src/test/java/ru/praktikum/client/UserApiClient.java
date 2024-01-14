package ru.praktikum.client;

import io.qameta.allure.Step;
import ru.praktikum.model.UserCredentials;
import ru.praktikum.model.UserData;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    @Step("Register user")
    public static String registerUser(UserData userData) {
        return given()
                .header("Content-type", "application/json")
                .body(userData)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("accessToken");
    }

    @Step("Auth user")
    public static String authUser(UserCredentials userCredentials) {
        return given()
                .header("Content-type", "application/json")
                .body(userCredentials)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("accessToken");
    }

    @Step("Remove user")
    public static void removeUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }
}
