package tests.api;

import models.Post;
import org.testng.annotations.Test;
import steps.api.ApiPostsSteps;
import utilities.configuration.TestDataProvider;

public class CreatePostTest {
    private final ApiPostsSteps apiPostsSteps = new ApiPostsSteps();

    @Test
    public void testCreatePost() {
        Post post = apiPostsSteps.getNewPostWithUserId(
                TestDataProvider.getPostRequestUserId());
        apiPostsSteps.verifyCreatedPostInformationCorrect(
                apiPostsSteps.getPostFromResponseForCreatePostByRequest(post), post);
    }
}
