package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;
import utilities.configuration.TestDataProvider;

public class GetPostWithIdTest {
    private final ApiPostsSteps apiPostsSteps = new ApiPostsSteps();

    @Test
    public void testGetPostWithId() {
        apiPostsSteps.verifyPostInformationCorrect(
                apiPostsSteps.getPostWithIdByRequest(TestDataProvider.getPostId()));
    }
}