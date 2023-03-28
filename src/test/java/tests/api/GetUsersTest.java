package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiUsersSteps;
import utilities.configuration.TestDataProvider;

public class GetUsersTest {
    @Test
    public static void testGetUsers() {
        ApiUsersSteps.verifyUserWithIdFromUsersEqualsTestUser(
                ApiUsersSteps.getUsersByRequest(),
                TestDataProvider.getTestUserId(),
                TestDataProvider.getTestUser());
    }
}
