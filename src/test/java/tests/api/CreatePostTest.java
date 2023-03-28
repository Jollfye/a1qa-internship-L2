package tests.api;

import models.Post;
import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;
import utilities.configuration.TestDataProvider;

public class CreatePostTest {
    @Test
    public static void testCreatePost() {
        Post post = ApiPostsSteps.getNewPostWithUserId(
                TestDataProvider.getPostRequestUserId());
        ApiPostsSteps.verifyCreatedPostInformationCorrect(
                ApiPostsSteps.getPostFromResponseForCreatePostByRequest(post), post);
    }
}
