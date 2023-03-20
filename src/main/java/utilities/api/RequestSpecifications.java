package utilities.api;

import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RequestSpecifications {
    public static RequestSpecification commonGiven() {
        return given()
                .baseUri(Configuration.getApiUrl())
                .param("v", Configuration.getApiVersion())
                .param("access_token", TestDataProvider.getAccessToken());
    }
}
