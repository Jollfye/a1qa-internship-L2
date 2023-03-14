package steps.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import utilities.api.RequestUtils;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

public class GetPostWithResponseBodyEmptyTestSteps extends BaseSteps {
    private Response response;

    public void getPostWithResponseBodyEmptyByRequest() {
        response = RequestUtils.sendGetRequest(
                Configuration.getPostsPath()
                        + TestDataProvider.getPostIdWithResponseBodyEmpty());
    }

    public void verifyResponseStatusCodeAndBodyType() {
        verifyResponseCodeAndContentType(response, HttpStatus.SC_NOT_FOUND, ContentType.JSON);
    }

    public void verifyResponseBodyEmpty() {
        JsonNode body = ResponseUtils.responseAs(response, JsonNode.class);
        Assert.assertTrue(body.isEmpty(),
                "Response body is not empty. Body: " + body);
    }
}
