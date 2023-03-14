package tests.api;

import org.testng.annotations.Test;
import steps.api.GetPostWithIdTestSteps;

public class GetPostWithIdTest {
    private final GetPostWithIdTestSteps getPostWithIdTestSteps = new GetPostWithIdTestSteps();

    @Test
    public void testGetPostWithId() {
        getPostWithIdTestSteps.getPostWithIdByRequest();
        getPostWithIdTestSteps.verifyResponseStatusCodeAndBodyType();
        getPostWithIdTestSteps.verifyResponsePostInformationCorrect();
    }
}
