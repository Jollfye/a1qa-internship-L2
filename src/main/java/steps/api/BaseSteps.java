package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.api.ResponseUtils;

public abstract class BaseSteps {
    public void verifyResponseCode(Response response, int expectedCode) {
        ResponseUtils.verifyResponseCode(response, expectedCode);
    }

    public void verifyResponseContentType(Response response, ContentType expectedContentType) {
        ResponseUtils.verifyResponseContentType(response, expectedContentType);
    }

    public void verifyResponseCodeAndContentType(Response response, int expectedCode, ContentType expectedContentType) {
        verifyResponseCode(response, expectedCode);
        verifyResponseContentType(response, expectedContentType);
    }
}
