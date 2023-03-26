package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import utilities.configuration.Configuration;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RequestSpecifications {
    public static RequestSpecification commonGiven() {
        return given()
                .baseUri(Configuration.getApiUrl())
                .contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8));
    }
}
