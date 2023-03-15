package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;

public class GetPostsTest {
    private final ApiPostsSteps apiPostsSteps = new ApiPostsSteps();

    @Test
    public void testGetPosts() {
        apiPostsSteps.verifyPostsSortedById(
                apiPostsSteps.getPostsByRequest(), true);
    }
}
