package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {
    public static void verifyResponseCode(Response response, int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    public static void verifyResponseContentType(Response response, ContentType expectedContentType) {
        response.then().contentType(expectedContentType);
    }

    public static <T> T responseAs(Response response, Class<T> aClass) {
        return response.then().extract().as(aClass);
    }
}
