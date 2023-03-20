package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ResponseUtils {
    public static Response getVerifiedResponseForGetRequest(
            RequestSpecification given, String path,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendGetRequest(given, path);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static Response getVerifiedResponseForGetRequestWithParams(
            RequestSpecification given,
            String path, Map<String, Object> params,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendGetRequestWithParams(given, path, params);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static Response getVerifiedResponseForPostRequestWithParams(
            RequestSpecification given,
            String path, Map<String, Object> params,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendPostRequestWithParams(given, path, params);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static Response getVerifiedResponseForPostRequestWithFile(
            RequestSpecification given,
            String path, String controlName, File file,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        Response response = RequestUtils.sendPostRequestWithFile(
                given, path, controlName, file);
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
