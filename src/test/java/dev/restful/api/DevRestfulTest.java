package dev.restful.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.restful.api.model.Data;
import dev.restful.api.model.Product;
import dev.restful.api.model.ProductResponse;
import dev.restful.api.specs.Specification;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class DevRestfulTest {
    RequestSpecification requestSpecification = Specification.requestSpecification();
    ResponseSpecification responseSpecification = Specification.responseSpecification();

    @Test
    public void shouldHaveCorrectMethodGetTest() {
        given()
                .spec(requestSpecification)
                .when()
                .log().method()
                .log().headers()
                .get()
                .then()
                .spec(responseSpecification);
    }

    @Test
    public void shouldHaveCorrectMethodPostTest() {
        given()
                .baseUri("https://api.restful-api.dev/objects")
                .when()
                .contentType(JSON)
                .body(
                        "{\n" +
                                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                                "   \"data\": {\n" +
                                "      \"year\": 2019,\n" +
                                "      \"price\": 1849.99,\n" +
                                "      \"CPU model\": \"Intel Core i9\",\n" +
                                "      \"Hard disk size\": \"1 TB\"\n" +
                                "   }\n" +
                                "}"
                )
                .log().method()
                .log().headers()
                .log().body()
                .post()
                .then()
                .spec(responseSpecification);
    }

    @Test
    public void shouldHaveCorrectMethodPost2Test() throws JsonProcessingException {
        Data data = new Data(2019, 1849.99, "Intel Core i9", "1 TB");
        Product product = new Product("Apple MacBook Pro 16", data);

        String productJson = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(product);

        ExtractableResponse<Response> response =
                given()
                        .baseUri("https://api.restful-api.dev/objects")
                        .when()
                        .contentType(JSON)
                        .body(productJson)
                        .log().method()
                        .log().headers()
                        .log().body()
                        .post()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract();

        ProductResponse product1 = response.body().jsonPath().getObject("", ProductResponse.class);
        System.out.println("product1 = " + product1);

//        assertEquals("2019", response.jsonPath().get("data.year").toString());
//        assertAll(
//                () -> assertEquals(Integer.valueOf(2019), response.jsonPath().get("data.year"), "Некорректный год"),
//                () -> assertEquals("Intel Core i9", response.jsonPath().get("data.'CPU model'"), "Некорректная модель CPU")
//        );
    }
}