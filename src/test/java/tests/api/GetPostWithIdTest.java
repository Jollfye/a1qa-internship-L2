package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;
import utilities.configuration.TestDataProvider;

public class GetPostWithIdTest {
    @Test
    public static void testGetPostWithId() {
        ApiPostsSteps.verifyPostInformationCorrect(
                ApiPostsSteps.getPostWithIdByRequest(TestDataProvider.getPostId()));
    }
}
