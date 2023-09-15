package utilities.api;

import constants.api.VkApiParam;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

import static io.restassured.RestAssured.given;

@UtilityClass
public class RequestSpecifications {
    public static RequestSpecification VkApiCommonGiven() {
        return given()
                .baseUri(Configuration.getApiUrl())
                .param(VkApiParam.VERSION, Configuration.getApiVersion())
                .param(VkApiParam.ACCESS_TOKEN, TestDataProvider.getAccessToken());
    }
}
