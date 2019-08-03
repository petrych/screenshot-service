package com.petrych.service.integration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.petrych.service.TestingEndpoints.LOCALHOST_ENDPOINT;
import static com.petrych.service.TestingEndpoints.SCREENSHOTS_ENDPOINT;
import static io.restassured.RestAssured.given;

public class FirstApiIT {

    private static RequestSpecification spec;

    @BeforeAll
    public static void setup() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(LOCALHOST_ENDPOINT.toString())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void getAllScreenshots_DB_Access_Ok() {
        given(spec)
                .when()
                .get(SCREENSHOTS_ENDPOINT.toString())
                .then()
                .statusCode(200);
    }


    @Test
    public void getScreenshot_Image_Exists() {
        given(spec).pathParam("id", 1)
                .when()
                .get(SCREENSHOTS_ENDPOINT.toString() + "/{id}")
                .then()
                .contentType("image/png")
                .statusCode(200);
    }


    @Test
    public void getScreenshot_Image_Not_Exists() {
        given(spec).pathParam("id", 3)
                .when()
                .get(SCREENSHOTS_ENDPOINT.toString() + "/{id}")
                .then()
                .statusCode(404);
    }


    @Test
    public void getScreenshot_Id_Not_Exists() {
        given(spec).pathParam("id", "k")
                .when()
                .get(SCREENSHOTS_ENDPOINT.toString() + "/{id}")
                .then()
                .statusCode(404);
    }

}
