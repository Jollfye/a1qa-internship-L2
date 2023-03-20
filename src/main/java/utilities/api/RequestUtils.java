package utilities.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Map;

@UtilityClass
public class RequestUtils {
    public static Response sendGetRequest(String path) {
        return RequestSpecifications.commonGiven().when().get(path);
    }

    public static Response sendGetRequestWithParams(String path, Map<String, Object> params) {
        RequestSpecification request = RequestSpecifications.commonGiven();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            request.param(entry.getKey(), entry.getValue());
        }
        return request.when().get(path);
    }

    public static Response sendPostRequestWithParams(String path, Map<String, Object> params) {
        RequestSpecification request = RequestSpecifications.commonGiven();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            request.param(entry.getKey(), entry.getValue());
        }
        return request.when().post(path);
    }

    public static Response sendPostRequestWithFile(String path, String controlName, File file) {
        return RequestSpecifications.commonGiven()
                .multiPart(controlName, file).when().post(path);
    }
}
