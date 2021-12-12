import entity.User;
import entity.UserAlbumId;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import service.DBService;

import java.io.IOException;
import java.sql.SQLException;

import static assets.Simple.*;


public class Example {
    private final static String posts = "posts";
    private final static String todos = "todos/11"; //  todos = id можно получить определенного юзера
    private final static String cnt = "/10";
    private final static String album = "photos/5";
    private final DBService dbService = new DBService();
    String jsonBody = generatedStringFromResource("src/test/properties/user.json");

    public Example() throws IOException {
    }

    @Test
    void userAlbum() throws SQLException, ClassNotFoundException {
        UserAlbumId as = getAlbum(album);
        dbService.saveAlbum(as);
        System.out.println(as);
    }

    @Test
    void getUp() throws SQLException, ClassNotFoundException {
        User user = getUser(todos);
        dbService.save(user);
        System.out.println(user);

    }

    @Test
    void posts() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "PL");
        jsonObject.put("body", "PL");
        jsonObject.put("completed", "PL");
        jsonObject.put("id", 35);
        User as = createUser(jsonBody, posts);
        dbService.save(as);
        System.out.println(as);

    }

    @Test
    void refactoringUser() throws SQLException, ClassNotFoundException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "put");
        jsonObject.put("completed", "put");
        jsonObject.put("id", 6);
        User as = put(jsonObject.toString(), posts, cnt);
        dbService.put(2, as);
        System.out.println(as);
    }

    @Test
    void fullRefactoringUser() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "patch");
        jsonObject.put("completed", "path");
        jsonObject.put("body", "path");
        jsonObject.put("id", "5");
        User as = paas(jsonObject, posts, cnt);
        dbService.put(5, as);
        System.out.println(as);


    }

    @Test
    void deleteSpecificUser() throws SQLException, ClassNotFoundException {
       User us = delete(posts, cnt);
        dbService.delete(101);
        System.out.println(us);



    }


}
