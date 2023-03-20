package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ResponseUtils {
    public static Response getVerifiedResponseForGetRequest(
            String path,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendGetRequest(path);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static Response getVerifiedResponseForGetRequestWithParams(
            String path, Map<String, Object> params,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendGetRequestWithParams(path, params);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static Response getVerifiedResponseForPostRequestWithParams(
            String path, Map<String, Object> params,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendPostRequestWithParams(path, params);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static Response getVerifiedResponseForPostRequestWithFile(
            String path, String controlName, File file,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendPostRequestWithFile(path, controlName, file);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static void verifyResponse(
            Response response,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        verifyResponseStatus(response, expectedStatus);
        verifyResponseContentType(response, expectedContentType);
        verifyResponsePathsNotNull(response, nonNullPaths);
    }

    public static void verifyResponseStatus(
            Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    public static void verifyResponseContentType(
            Response response, ContentType expectedContentType) {
        response.then().contentType(expectedContentType);
    }

    public static void verifyResponsePathsNotNull(
            Response response, List<String> nonNullPaths) {
        nonNullPaths.forEach(path -> response.then().body(path, Matchers.notNullValue()));
    }

    public static void verifyResponsePathValue(
            Response response, String path, Object expectedValue) {
        response.then().body(path, Matchers.equalTo(expectedValue));
    }

    public static void verifyResponseContainsItem(
            Response response, String path, Object expectedItem) {
        response.then().body(path, Matchers.hasItem(expectedItem));
    }

}
