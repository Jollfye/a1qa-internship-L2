package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiUsersSteps;
import utilities.configuration.TestDataProvider;

public class GetUsersTest {
    private final ApiUsersSteps apiUsersSteps = new ApiUsersSteps();

    @Test
    public void testGetUsers() {
        apiUsersSteps.verifyUserWithIdFromUsersEqualsTestUser(
                apiUsersSteps.getUsersByRequest(),
                TestDataProvider.getTestUserId(),
                TestDataProvider.getTestUser());
    }
}
