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

import java.util.List;

public class GetUsersTestSteps extends BaseSteps {
    private Response response;

    public void getUsersByRequest() {
        response = RequestUtils.sendGetRequest(Configuration.getUsersPath());
    }

    public void verifyResponseStatusCodeAndBodyType() {
        verifyResponseCodeAndContentType(response, HttpStatus.SC_OK, ContentType.JSON);
    }

    public void verifyResponseUserWithIdEqualsTestUser() {
        List<User> users = List.of(ResponseUtils.responseAs(response, User[].class));
        User user = users.stream()
                .filter(u -> u.getId().equals(TestDataProvider.getTestUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Test user is not found. Test user id: %d",
                                TestDataProvider.getTestUserId())));
        Assert.assertEquals(user, TestDataProvider.getTestUser(), "Users are not equal");
    }
}
