package restStepsDefs;

import entity.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class JsonPlaceholderApi {
    private final static String url = "https://jsonplaceholder.typicode.com/";
    private final static String todos = "todos/"; //todos = id - можно получить определенного юзера
    private final static String endPointPosts = "posts";
    private final static String cnt = "/10";
    private final static String album = "photos/";


    /**
     * С помощью данного метода получаем  юзера
     * int userId - номер юзера
     */
    public Response getUser(int userId) {
        return given()
                .baseUri(url + todos)
                .contentType(ContentType.JSON)
                .get(String.valueOf(userId));
    }

    /**
     * С помощью данного метода создаем юзера
     */

    public Response createUser(User user) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(user)
                .post(endPointPosts);
    }

    /**
     * С помощью данного метода рефакторим юзера - методом put
     */

    public Response put(User user) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(user)
                .put(endPointPosts.concat(cnt));

    }
    /**
     * С помощью данного метода рефакторим юзера - методом path
     */
    public Response path(User user) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(user)
                .patch(endPointPosts.concat(cnt));
    }
    /**
     * С помощью данного метода удаляет юзер
     */
    public Response delete() {
        return given()
                .baseUri(url)
                .delete(endPointPosts.concat(cnt));
    }
    /**
     * С помощью данного метода получаем ссылки на картинки
     */
    public Response getAlbum(int id) {
        return given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .get(album +  String.valueOf(id));
    }

}
