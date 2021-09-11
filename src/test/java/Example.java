import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Example {
    static String token;
    static String act;
    static String mrcu;
    static String cookie;
    static String str;
    static String cookie1;
    static String cookie2;

    @BeforeEach
    @DisplayName("Get")
    void get() {

        Response response = (Response) given()
                .when()
                .get("https://mail.ru")
                .then().statusCode(200).extract().response();
        System.out.println(response.headers().toString());
        System.out.println(cookie = response.getCookies() + " Cookie");
    }

    @Test
    void Auth() {
        System.out.println("Авторизация");
        RestAssured.baseURI = "https://auth.mail.ru".concat("/jsapi/auth");
        ValidatableResponse response = RestAssured.given()
                .auth()
                .basic("leva.trapeznikova@mail.ru", "Oneninenine8")
                .queryParam("saveauth", "1")
                .post()
                .then().statusCode(200);


        System.out.println(response.extract().body().asString());
      /*  str = response.extract().body().path("act");
        mrcu = response.extract().body().path("mrcu");*/

    }

   /* public void getDelete(String act, String mrcu,String token) {
        RestAssured.baseURI = "https://e.mail.ru/".concat("sdc?token="); // https://e.mail.ru/sdc?token=8817d8c5db0e4067a2c6d341708bf2ce
        ValidatableResponse response1 = RestAssured.given()
                .cookie("act",act, cookie1).cookie("mrcu",mrcu,cookie2)
                .queryParam("token",token,new Object[0])
                .when()
                .get().then().statusCode(200).log().all();
        token = response1.extract().body().path("token").toString();
        System.out.println(response1.extract().cookies().get("act") + " act TOKEN");
        System.out.println(response1.extract().cookies().get("mrcu") + " mrcu TOKEN");
        System.out.println(response1.extract().body().path("token").toString() + " Really TOKEN");
        System.out.println(response1.extract().body().asString());

    }


    public void Delete(String token){
        RestAssured.baseURI = "https://e.mail.ru/".concat("api/v1/threads/status/smart?");
        ValidatableResponse response2 = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .cookie("act",act)
                .cookie("mrcu",mrcu)
                .queryParam("folder","0")
                .queryParam("limit","50")
                .queryParam("filters","{}")
                .queryParam("last_modified","1631255909")
                .queryParam("force_custom_thread","true")
                .queryParam("supported_custom_metathreads","[\"tomyself\"]")
                .queryParam("offset","0")
                .queryParam("email","leva.trapeznikova@mail.ru")
                .queryParam("htmlencoded","false")
                .queryParam("token",token)
                .when().post().then().statusCode(200);


        System.out.println(response2.extract().body().asString() + "????????304");
        System.out.println();

    }*/

}
