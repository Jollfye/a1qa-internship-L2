package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import models.user.User;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;

import java.util.List;

@UtilityClass
public class ApiUsersSteps {
    public static List<User> getUsersByRequest() {
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                Configuration.getUsersPath(), HttpStatus.SC_OK, ContentType.JSON);
        return List.of(ResponseUtils.responseAs(response, User[].class));
    }

    public static void verifyUserWithIdFromUsersEqualsTestUser(List<User> users, int userId, User testUser) {
        User user = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("User with id: %d is not found in the users list", userId)));
        verifyUserEqualsTestUser(user, testUser);
    }

    public static User getUserWithIdByRequest(int userId) {
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                Configuration.getUsersPath() + userId,
                HttpStatus.SC_OK, ContentType.JSON);
        return ResponseUtils.responseAs(response, User.class);
    }

    public static void verifyUserEqualsTestUser(User user, User testUser) {
        Assert.assertEquals(user, testUser, "Users are not equal");
    }
}
