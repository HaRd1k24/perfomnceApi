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
    private static String cookie;
    private static String cookie1;

    @Test
    void Auth() {
        Date date = new Date();
        HashMap<String, Object> map = new HashMap<>();
        Response response = (Response) given()
                .when()
                .get("https://mail.ru")
                .then().statusCode(200).extract().response();
        Map<String, String> cookies = response.getCookies();
        System.out.println(cookies);
        System.out.println("Авторизация");
        RestAssured.baseURI = "https://auth.mail.ru".concat("/jsapi/auth");
        ValidatableResponse response1 = RestAssured.given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .cookie(cookies.get("act")).cookie(cookies.get("mrcu"))
                .queryParam("Accept","*/*")
                .queryParam("AcceptEncoding","gzip, deflate, br")
                .queryParam("AcceptLaunge","ru-RU,ru;q=0.9")
                .queryParam("Connection","keep-alive")
                .queryParam("Content-Length","122")
                .queryParam("Content-Type","application/x-www-form-urlencoded")
                .queryParam("Host","auth.mail.ru")
                .queryParam("Origin","https://mail.ru")
                .queryParam("Referer","https://mail.ru/")
                .queryParam("sec-ch-ua","\"Chromium\";v=\"94\", \"Google Chrome\";v=\"94\", \";Not A Brand\";v=\"99\"")
                .queryParam("sec-ch-ua-mobile","?0")
                .queryParam("sec-ch-ua-platform","\"Windows\"")
                .queryParam("Sec-Fetch-Dest","empty")
                .queryParam("Sec-Fetch-Mode","cors")
                .queryParam("Sec-Fetch-Site","same-site")
                .queryParam("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")
                .formParam("login", "leva.trapeznikov@mail.ru")
                .formParam("password", "Oneninenine8")
                .formParam("saveauth", "1")
                .formParam("01 Oct 2020 21:00:07 GMT")
              //  .formParam("token","c9c4a762d0fa474dbb339ee95ea80312")
                .formParam("project", "e.mail.ru")

                .post()
                .then().statusCode(200);


        System.out.println(response1.extract().body().asString());
      /*  str = response.extract().body().path("act");
        mrcu = response.extract().body().path("mrcu");*/

    }

  /*  @BeforeEach
    public void Delete() {
        RestAssured.baseURI = "https://e.mail.ru".concat("/messages/inbox/?back=1");
        ValidatableResponse response2 = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .queryParam("back", "1")
                .when().get().then().statusCode(200);
        System.out.println(response2.extract().headers().toString());

    }*/

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
