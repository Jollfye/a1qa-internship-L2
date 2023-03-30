package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ResponseUtils {
    public static ResponseWrapper getVerifiedResponseForGetRequest(
            RequestSpecification given, String path,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        ResponseWrapper response = RequestUtils.sendGetRequest(given, path);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static ResponseWrapper getVerifiedResponseForGetRequestWithParams(
            RequestSpecification given,
            String path, Map<String, Object> params,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        ResponseWrapper response = RequestUtils.sendGetRequestWithParams(given, path, params);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static ResponseWrapper getVerifiedResponseForPostRequestWithParams(
            RequestSpecification given,
            String path, Map<String, Object> params,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        ResponseWrapper response = RequestUtils.sendPostRequestWithParams(given, path, params);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static ResponseWrapper getVerifiedResponseForPostRequestWithFile(
            RequestSpecification given,
            String path, String controlName, File file,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        ResponseWrapper response = RequestUtils.sendPostRequestWithFile(
                given, path, controlName, file);
        verifyResponse(response, expectedStatus, expectedContentType, nonNullPaths);
        return response;
    }

    public static void verifyResponse(
            ResponseWrapper response,
            int expectedStatus, ContentType expectedContentType,
            List<String> nonNullPaths) {
        verifyResponseStatus(response, expectedStatus);
        verifyResponseContentType(response, expectedContentType);
        verifyResponsePathsNotNull(response, nonNullPaths);
    }

    public static void verifyResponseStatus(ResponseWrapper response, int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus,
                String.format("Response status code is not %d", expectedStatus));
    }

    public static void verifyResponseContentType(ResponseWrapper response, ContentType expectedContentType) {
        Assert.assertTrue(response.getContentType().contains(expectedContentType.toString()),
                String.format("Response content type is not '%s'", expectedContentType));
    }

    public static void verifyResponsePathsNotNull(ResponseWrapper response, List<String> nonNullPaths) {
        nonNullPaths.forEach(path -> Assert.assertNotNull(response.getString(path),
                String.format("Response path '%s' is null", path)));
    }

    public static void verifyResponsePathValue(ResponseWrapper response, String path, Object expectedValue) {
        Assert.assertEquals(response.getString(path), String.valueOf(expectedValue),
                String.format("Response '%s' by path '%s' is not '%s'",
                        response.getString(path), path, expectedValue));
    }

    public static void verifyResponseContainsItem(ResponseWrapper response, String path, Object expectedItem) {
        Assert.assertTrue(response.getString(path).contains(String.valueOf(expectedItem)),
                String.format("Response '%s' by path '%s' does not contain '%s'",
                        response.getString(path), path, expectedItem));
    }
}
