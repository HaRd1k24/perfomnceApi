import restStepsDefs.JsonPlaceholderApi;
import entity.User;
import entity.UserAlbumId;
import org.junit.jupiter.api.Test;
import service.DBService;

import java.sql.SQLException;



public class ApiTest {
    private final DBService dbService = new DBService();
    JsonPlaceholderApi api = new JsonPlaceholderApi();
    User user = new User();

    @Test
    void userAlbum() throws SQLException, ClassNotFoundException {
        UserAlbumId as = api.getAlbum(4)
                .then().statusCode(200).extract().as(UserAlbumId.class);
        dbService.saveAlbum(as);
        System.out.println(as);
    }

    @Test
    void getUser() throws SQLException, ClassNotFoundException {
        User us = api.getUser(11)
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);
        dbService.save(us);
        System.out.println(us);

    }

    @Test
    void createUser() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setTitle("post");
        user.setCompleted("post");
        user.setId(6);
        user.setBody("post");
        User as = api.createUser(user)
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);
        dbService.save(as);
        System.out.println(as);

    }

    @Test
    void refactoringUser() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setTitle("put");
        user.setCompleted("put");
        user.setId(6);
        User as = api.put(user)
                        .then().statusCode(200).extract().as(User.class);
        dbService.put(2, as);
        System.out.println(as);
    }

    @Test
    void fullRefactoringUser() throws SQLException, ClassNotFoundException {
        user.setTitle("patch");
        user.setCompleted("path");
        user.setBody("path");
        user.setId(5);
        User as = api.path(user)
                .then().statusCode(200).extract().as(User.class);
        dbService.put(5, as);
        System.out.println(as);


    }

    @Test
    void deleteSpecificUser() throws SQLException, ClassNotFoundException {
        api.delete()
                .then().statusCode(200);
        dbService.delete(11);


    }


}
