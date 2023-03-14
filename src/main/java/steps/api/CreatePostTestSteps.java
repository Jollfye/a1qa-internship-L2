package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import utilities.RandomUtils;
import utilities.api.RequestUtils;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

public class CreatePostTestSteps extends BaseSteps {
    private final Post post = new Post()
            .userId(TestDataProvider.getPostRequestUserId())
            .postBody(RandomUtils.getRandomAlphanumeric(
                    TestDataProvider.getRandomAlphanumericLength()))
            .title(RandomUtils.getRandomAlphanumeric(
                    TestDataProvider.getRandomAlphanumericLength()));
    private Response response;

    public void createPostByRequest() {
        response = RequestUtils.sendPostRequest(Configuration.getPostsPath(), post);
    }

    public void verifyResponseStatusCodeAndBodyType() {
        verifyResponseCodeAndContentType(response, HttpStatus.SC_CREATED, ContentType.JSON);
    }

    public void verifyResponsePostInformationCorrect() {
        Post createdPost = ResponseUtils.responseAs(response, Post.class);
        Assert.assertEquals(createdPost.getTitle(), post.getTitle(),
                "Titles are not equal");
        Assert.assertEquals(createdPost.getPostBody(), post.getPostBody(),
                "Post bodies are not equal");
        Assert.assertEquals(createdPost.getUserId(), post.getUserId(),
                "User ids are not equal");
        Assert.assertNotNull(createdPost.getId(),
                "Post id is null");
    }
}
