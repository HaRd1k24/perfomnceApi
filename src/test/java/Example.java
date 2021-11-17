import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;

public class Example {
    static Properties properties = new Properties();
    private final static String posts = "posts";
    private final static String todos = "todos/15"; //  todos = id можно получить определенного юзера
    private final static String cnt = "/1";
    private final static String url = "http://jsonplaceholder.typicode.com/";

    @Test
    void getUp() {
        JSONObject jsonObject = new JSONObject();
        ValidatableResponse response2 = RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .get(todos)
                .then().statusCode(200);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void create() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "PL");
        jsonObject.put("body", "PL");
        jsonObject.put("userId", 1);
        ValidatableResponse response2 = RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .post(posts)
                .then().statusCode(201);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void put() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "put");
        jsonObject.put("body", "put");
        jsonObject.put("userId", 1);
        jsonObject.put("id", 1);
        ValidatableResponse response2 = RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .post(posts)
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

        ValidatableResponse response2 = RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .patch(posts.concat(cnt))
                .then().statusCode(200);

        System.out.println(response2.extract().body().asString());
    }

    @Test
    void delete() {
        ValidatableResponse response2 = (ValidatableResponse) RestAssured.given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .delete(posts.concat(cnt))
                .then().statusCode(200);

        System.out.println(response2.extract().body().asString());
    }

}
