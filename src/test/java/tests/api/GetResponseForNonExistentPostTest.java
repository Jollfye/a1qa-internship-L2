package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;
import utilities.configuration.TestDataProvider;

public class GetResponseForNonExistentPostTest {
    @Test
    public static void testGetResponseForNonExistentPost() {
        ApiPostsSteps.verifyResponseBodyEmpty(
                ApiPostsSteps.getResponseForNonExistentPostWithId(
                        TestDataProvider.getNonExistentPostId()));
    }
}
