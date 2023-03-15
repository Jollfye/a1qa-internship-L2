package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {
    public static Response getVerifiedResponseForGetRequest(
            String endpoint, int expectedStatus, ContentType expectedContentType) {
        Response response = RequestUtils.sendGetRequest(endpoint);
        verifyResponseStatusAndContentType(
                response, expectedStatus, expectedContentType);
        return response;
    }

    public static Response getVerifiedResponseForPostRequest(
            String endpoint, Object body, int expectedStatus, ContentType expectedContentType) {
        Response response = RequestUtils.sendPostRequest(endpoint, body);
        verifyResponseStatusAndContentType(
                response, expectedStatus, expectedContentType);
        return response;
    }

    public static void verifyResponseStatusAndContentType(
            Response response, int expectedStatus, ContentType expectedContentType) {
        verifyResponseStatus(response, expectedStatus);
        verifyResponseContentType(response, expectedContentType);
    }

    public static void verifyResponseStatus(Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    public static void verifyResponseContentType(Response response, ContentType expectedContentType) {
        response.then().contentType(expectedContentType);
    }

    public static <T> T responseAs(Response response, Class<T> aClass) {
        return response.then().extract().as(aClass);
    }
}
