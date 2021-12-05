import entity.User;
import entity.UserAlbumId;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import service.DBService;

import java.sql.SQLException;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Example {
    private final static String posts = "posts";
    private final static String todos = "todos/6"; //  todos = id можно получить определенного юзера
    private final static String cnt = "/10";
    private final static String url = "http://jsonplaceholder.typicode.com/";
    private final static String album = "photos/5";

    private final DBService dbService = new DBService();

    @Test
    void getAlbum() throws SQLException, ClassNotFoundException {
        UserAlbumId as =  given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .headers("content-type", "application/json")
                .get(album)
                .then().extract().as(UserAlbumId.class);
        dbService.saveAlbum( as);
    }

    @Test
    void getUp() throws SQLException, ClassNotFoundException {
        User user = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .get(todos)
                .then().extract().as(User.class);
        System.out.println(user.toString());
        dbService.save(user);

    }


    @Test
    void create() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "PL");
        jsonObject.put("body", "PL");
        jsonObject.put("completed", "PL");
        jsonObject.put("id", 35);
        User as = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .post(posts)
                .then().extract().as(User.class);
        dbService.save(as);

    }

    @Test
    void put() throws SQLException, ClassNotFoundException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "put");
        jsonObject.put("completed", "put");
        jsonObject.put("id", 100);
        User as = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .put(posts.concat(cnt))
                .then().extract().as(User.class);

        System.out.println(as.toString());
        dbService.put(2, as);
    }

    @Test
    void patch() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "patch");
        jsonObject.put("completed", "path");
        jsonObject.put("body", "path");
        jsonObject.put("id", "5");
        User as = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers("content-type", "application/json")
                .patch(posts.concat(cnt))
                .then().extract().as(User.class);
        dbService.put(5, as);
        System.out.println(as.toString());

    }

    @Test
    void delete() throws SQLException, ClassNotFoundException {
        User as = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .delete(posts.concat(cnt))
                .then().extract().as(User.class);

        dbService.delete(5, as);

        System.out.println(as);
    }


}
