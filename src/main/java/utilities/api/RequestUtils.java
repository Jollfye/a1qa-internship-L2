package utilities.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Map;

@UtilityClass
public class RequestUtils {
    public static Response sendGetRequest(RequestSpecification given, String path) {
        return given.when().get(path);
    }

    public static Response sendPostRequest(RequestSpecification given, String path) {
        return given.when().post(path);
    }

    public static Response sendGetRequestWithParams(
            RequestSpecification given, String path, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            given.param(entry.getKey(), entry.getValue());
        }
        return sendGetRequest(given, path);
    }

    public static Response sendPostRequestWithParams(
            RequestSpecification given, String path, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            given.param(entry.getKey(), entry.getValue());
        }
        return sendPostRequest(given, path);
    }

    public static Response sendPostRequestWithFile(
            RequestSpecification given, String path, String controlName, File file) {
        return sendPostRequest(given.multiPart(controlName, file), path);
    }
}
