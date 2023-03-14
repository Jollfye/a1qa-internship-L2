package utilities.api;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestUtils {
    public static Response sendGetRequest(String endpoint) {
        return RequestSpecifications.commonGiven().when().get(endpoint);
    }

    public static Response sendPostRequest(String endpoint, Object body) {
        return RequestSpecifications.commonGiven().body(body).when().post(endpoint);
    }
}
