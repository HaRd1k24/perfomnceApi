import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Example {

    final String token = "33754969b51d40db8adeb5c8068c1b1b";

    @Test
    @DisplayName("Авторизация")
    public void getUser() {
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("client_id", "123");
        map.put("client_secret", "234");
        map.put("username", "lev-trapeznikov@mail.ru");
        map.put("password", "Eaa4HPpOyu2%");

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(map)
                .when()
                .post("https://appsmail.ru/oauth/token")
                .then().log().all().extract().asString();
        System.out.println(response);
    }

    @Test
    @DisplayName("Авторизация и подтверждение")
    public void acceptUser() {
        given().when().cookie("9bdc3d9beb2da0bc4ef6f6108afb6592:570440636667416e190d50535a00550c50515101515807575602515702540708000f505f5b0e0409031654475c6e4206")
                .get("https://e.mail.ru/inbox/")
                .then().statusCode(200).log().all();
    }

    @Test
    @DisplayName("Удаление сообщений")
    public void deleteLetter() {
        given().cookie("19e13e22fbf409b0c834c567378196c7:5b5441637b5b0e0019050b52080054070052515501090e5705550c020051040f0704060108010753061654475c6e4206").when()
                .delete("https://e.mail.ru/inbox/")
                .then().statusCode(200).log().all();
    }

}
