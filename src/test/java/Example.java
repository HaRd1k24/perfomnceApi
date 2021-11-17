import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Example {

    @Test
    void getUp() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com".concat("/posts");
        ValidatableResponse response2 = RestAssured.given()
                .accept(ContentType.JSON)
                .get()
                .then().statusCode(200);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void create() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "PL");
        jsonObject.put("body", "PL");
        jsonObject.put("userId", 1);
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com".concat("/posts");
        ValidatableResponse response2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .post()
                .then().statusCode(201);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void put() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "321");
        jsonObject.put("body", "321");
        jsonObject.put("userId", 1);
        jsonObject.put("id", 1);
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com".concat("/posts");
        ValidatableResponse response2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .post()
                .then().statusCode(201);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void patch() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "patch");
        jsonObject.put("body", "path");
        jsonObject.put("userId", 1);
        jsonObject.put("id", "5");

        RestAssured.baseURI = "http://jsonplaceholder.typicode.com".concat("/posts/1");
        ValidatableResponse response2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .patch()
                .then().statusCode(200);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void delete() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com".concat("/posts/1");
        ValidatableResponse response2 = RestAssured.given()
                .delete()
                .then().statusCode(200);

        System.out.println(response2.extract().body().asString());
    }

}
