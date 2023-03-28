package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiUsersSteps;
import utilities.configuration.TestDataProvider;

public class GetUserWithIdTest {
    @Test
    public static void testGetUserWithId() {
        ApiUsersSteps.verifyUserEqualsTestUser(
                ApiUsersSteps.getUserWithIdByRequest(
                        TestDataProvider.getTestUserId()),
                TestDataProvider.getTestUser());
    }
}
