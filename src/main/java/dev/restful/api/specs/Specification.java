package dev.restful.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.restful-api.dev/objects")
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }
}