package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import utilities.api.RequestUtils;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

public class GetPostWithIdTestSteps extends BaseSteps {
    private Response response;

    public void getPostWithIdByRequest() {
        response = RequestUtils.sendGetRequest(
                Configuration.getPostsPath() + TestDataProvider.getPostId());
    }

    public void verifyResponseStatusCodeAndBodyType() {
        verifyResponseCodeAndContentType(response, HttpStatus.SC_OK, ContentType.JSON);
    }

    public void verifyResponsePostInformationCorrect() {
        Post post = ResponseUtils.responseAs(response, Post.class);
        Assert.assertEquals(post.getUserId(), TestDataProvider.getPostUserId(),
                "User ids are not equal");
        Assert.assertEquals(post.getId(), TestDataProvider.getPostId(),
                "Post ids are not equal");
        Assert.assertFalse(post.getTitle().isEmpty(),
                "Post title is empty");
        Assert.assertFalse(post.getPostBody().isEmpty(),
                "Post body is empty");
    }
}
