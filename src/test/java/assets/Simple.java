package assets;

import entity.User;
import entity.UserAlbumId;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Simple {
    private final static String url = "https://jsonplaceholder.typicode.com/";

    public static User getUser(String endpoint) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .get(endpoint)
                .then().statusCode(200).extract().as(User.class);
    }

    public static User createUser(String object, String endpoint) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(object)
                .headers("content-type", "application/json")
                .post(endpoint)
                .then().statusCode(201).extract().as(User.class);
    }

    public static User put(String jsonObject, String endpoint, String concat) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .headers("content-type", "application/json")
                .put(endpoint.concat(concat))
                .then().statusCode(200).extract().as(User.class);
    }

    public static User paas(JSONObject object, String endpoint, String concat) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(object.toString())
                .headers("content-type", "application/json")
                .patch(endpoint.concat(concat))
                .then().statusCode(200).extract().as(User.class);
    }

    public static User delete(String endpoint, String concat) {
        return given()
                .baseUri(url)
                .delete(endpoint.concat(concat))
                .then().statusCode(200).extract().as((Type) User.class);
    }

    public static UserAlbumId getAlbum(String endpoint) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .headers("content-type", "application/json")
                .get(endpoint)
                .then().statusCode(200).extract().as(UserAlbumId.class);
    }

    public static String generatedStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
