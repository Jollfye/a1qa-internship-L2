package tests.api;

import org.testng.annotations.Test;
import steps.api.GetPostsTestSteps;

public class GetPostsTest {
    private final GetPostsTestSteps getPostsTestSteps = new GetPostsTestSteps();

    @Test
    public void testGetPosts() {
        getPostsTestSteps.getPostsByRequest();
        getPostsTestSteps.verifyResponseStatusCodeAndBodyType();
        getPostsTestSteps.verifyResponsePostsSortedAscendingById();
    }
}
