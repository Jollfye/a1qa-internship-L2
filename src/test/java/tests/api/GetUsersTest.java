package tests.api;

import org.testng.annotations.Test;
import steps.api.GetUsersTestSteps;

public class GetUsersTest {
    private final GetUsersTestSteps getUsersTestSteps = new GetUsersTestSteps();

    @Test
    public void testGetUsers() {
        getUsersTestSteps.getUsersByRequest();
        getUsersTestSteps.verifyResponseStatusCodeAndBodyType();
        getUsersTestSteps.verifyResponseUserWithIdEqualsTestUser();
    }
}
