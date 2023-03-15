package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;
import utilities.configuration.TestDataProvider;

public class GetResponseForNonExistentPostTest {
    private final ApiPostsSteps apiPostsSteps = new ApiPostsSteps();

    @Test
    public void testGetResponseForNonExistentPost() {
        apiPostsSteps.verifyResponseBodyEmpty(
                apiPostsSteps.getResponseForNonExistentPostWithId(
                        TestDataProvider.getNonExistentPostId()));
    }
}
