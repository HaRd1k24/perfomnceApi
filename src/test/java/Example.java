import io.restassured.authentication.OAuthSignature;
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
        map.put("Login", "lev-trapeznikov");
        map.put("password", "Eaa4HPpOyu2%");

        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON).accept(ContentType.JSON).log().all()
                .when()
                .post("https://auth.mail.ru/cgi-bin/auth")
                .then().statusCode(302).log().all().extract().response();

        /*String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(map)
                .when()
                .post("https://auth.mail.ru/cgi-bin/auth")
                .then().statusCode(302).log().all().extract().asString();
        System.out.println(response);*/
    }

    @Test
    @DisplayName("Авторизация и подтверждение")
    public void acceptUser() {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).when().cookie("9bdc3d9beb2da0bc4ef6f6108afb6592:570440636667416e190d50535a00550c50515101515807575602515702540708000f505f5b0e0409031654475c6e4206")
                .get("https://e.mail.ru/inbox/")
                .then().statusCode(200).log().all();

    }

    @Test
    @DisplayName("Удаление сообщений")
    public void deleteLetter() {
        given().when()
                .delete("https://e.mail.ru/api/v1/threads/move")
                .then().statusCode(200).log().all();
    }

}
