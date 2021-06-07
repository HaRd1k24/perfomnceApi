import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Example {

    public static RequestSpecification spec;
    static String url = "https://mail.ru";
    final String token = "33754969b51d40db8adeb5c8068c1b1b";

    @BeforeAll
    static void setUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder
                .setBaseUri(url)
                .log(LogDetail.ALL);
        spec = builder.build();
    }

    @Test
    @DisplayName("Авторизация")
    public void getUser() {
        given(spec).
                when().post(token).
                then().statusCode(302).log().all();

    }

    @Test
    @DisplayName("Авторизация и подтверждение")
    public void acceptUser(){
        given(spec).when().
                get("9bdc3d9beb2da0bc4ef6f6108afb6592:570440636667416e190d50535a00550c50515101515807575602515702540708000f505f5b0e0409031654475c6e4206")
                .then().statusCode(200).log().all();
    }

    @Test
    @DisplayName("Удаление сообщений")
    public void deleteLetter(){
        given(spec).when()
                .delete("19e13e22fbf409b0c834c567378196c7:5b5441637b5b0e0019050b52080054070052515501090e5705550c020051040f0704060108010753061654475c6e4206")
                .then().statusCode(200).log().all();
    }

}
