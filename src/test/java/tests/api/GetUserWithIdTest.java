package tests.api;

import org.testng.annotations.Test;
import steps.api.GetUserWithIdTestSteps;

public class GetUserWithIdTest {
    private final GetUserWithIdTestSteps getUserWithIdTestSteps = new GetUserWithIdTestSteps();

    @Test
    public void testGetUserWithId() {
        getUserWithIdTestSteps.getUserWithIdByRequest();
        getUserWithIdTestSteps.verifyResponseStatusCodeAndBodyType();
        getUserWithIdTestSteps.verifyResponseUserEqualsTestUser();
    }
}
