package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiUsersSteps;
import utilities.configuration.TestDataProvider;

public class GetUserWithIdTest {
    private final ApiUsersSteps apiUsersSteps = new ApiUsersSteps();

    @Test
    public void testGetUserWithId() {
        apiUsersSteps.verifyUserEqualsTestUser(
                apiUsersSteps.getUserWithIdByRequest(
                        TestDataProvider.getTestUserId()),
                TestDataProvider.getTestUser());
    }
}
