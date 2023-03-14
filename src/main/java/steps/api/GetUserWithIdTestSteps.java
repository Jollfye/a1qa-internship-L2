package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.user.User;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import utilities.api.RequestUtils;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

public class GetUserWithIdTestSteps extends BaseSteps {
    private Response response;

    public void getUserWithIdByRequest() {
        response = RequestUtils.sendGetRequest(
                Configuration.getUsersPath() + TestDataProvider.getTestUserId());
    }

    public void verifyResponseStatusCodeAndBodyType() {
        verifyResponseCodeAndContentType(response, HttpStatus.SC_OK, ContentType.JSON);
    }

    public void verifyResponseUserEqualsTestUser() {
        User user = ResponseUtils.responseAs(response, User.class);
        Assert.assertEquals(user, TestDataProvider.getTestUser(), "Users are not equal");
    }
}
