package tests.api;

import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;

public class GetPostsTest {
    @Test
    public static void testGetPosts() {
        ApiPostsSteps.verifyPostsSortedById(
                ApiPostsSteps.getPostsByRequest(), true);
    }
}
