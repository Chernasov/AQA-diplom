package ru.netology.page;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.netology.data.DataHelper;

import static io.restassured.RestAssured.given;

public class ApiPage {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
//            .log(LogDetail.ALL)
            .build();

    public String sendPostStatus200(DataHelper.AuthInfo holder, String path) {
        String status =
                given()
                        .spec(requestSpec)
                        .body(holder)
                        .when()
                        .post(path)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("status");
        return status;
    }

    public int sendPostStatus500(DataHelper.AuthInfo holder, String path) {
        int status =
                given()
                        .spec(requestSpec)
                        .body(holder)
                        .when()
                        .post(path)
                        .then()
                        .statusCode(500)
                        .extract()
                        .path("status");
        return status;
    }

    public int sendPostStatus400(DataHelper.AuthInfo holder, String path) {
        int status =
                given()
                        .spec(requestSpec)
                        .body(holder)
                        .when()
                        .post(path)
                        .then()
                        .statusCode(400)
                        .extract()
                        .path("status");
        return status;
    }
}
