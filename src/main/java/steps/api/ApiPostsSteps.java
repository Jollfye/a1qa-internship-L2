package steps.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import models.Post;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import utilities.RandomUtils;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;
import utilities.configuration.TestDataProvider;

import java.util.Comparator;
import java.util.List;

@UtilityClass
public class ApiPostsSteps {
    public static List<Post> getPostsByRequest() {
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                Configuration.getPostsPath(), HttpStatus.SC_OK, ContentType.JSON);
        return List.of(ResponseUtils.responseAs(response, Post[].class));
    }

    public static void verifyPostsSortedById(List<Post> posts, boolean ascending) {
        List<Integer> ids = posts.stream()
                .map(Post::getId)
                .toList();
        List<Integer> sortedIds = ids.stream()
                .sorted(ascending ?
                        Comparator.naturalOrder() :
                        Comparator.reverseOrder())
                .toList();
        Assert.assertEquals(ids, sortedIds,
                String.format("Posts are not sorted %s (by id)",
                        ascending ? "ascending" : "descending"));
    }

    public static Post getPostWithIdByRequest(int postId) {
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                Configuration.getPostsPath() + postId,
                HttpStatus.SC_OK, ContentType.JSON);
        return ResponseUtils.responseAs(response, Post.class);
    }

    public static void verifyPostInformationCorrect(Post post) {
        Assert.assertEquals(post.getUserId(), TestDataProvider.getPostUserId(),
                "User ids are not equal");
        Assert.assertEquals(post.getId(), TestDataProvider.getPostId(),
                "Post ids are not equal");
        Assert.assertFalse(post.getTitle().isEmpty(),
                "Post title is empty");
        Assert.assertFalse(post.getPostBody().isEmpty(),
                "Post body is empty");
    }

    public static Response getResponseForNonExistentPostWithId(int nonExistentPostId) {
        return ResponseUtils.getVerifiedResponseForGetRequest(
                Configuration.getPostsPath() + nonExistentPostId,
                HttpStatus.SC_NOT_FOUND, ContentType.JSON);
    }

    public static void verifyResponseBodyEmpty(Response response) {
        JsonNode body = ResponseUtils.responseAs(response, JsonNode.class);
        Assert.assertTrue(body.isEmpty(),
                "Response body is not empty. Body: " + body);
    }

    public static Post getNewPostWithUserId(int userId) {
        return new Post()
                .userId(userId)
                .postBody(RandomUtils.getRandomAlphanumeric(
                        TestDataProvider.getRandomAlphanumericLength()))
                .title(RandomUtils.getRandomAlphanumeric(
                        TestDataProvider.getRandomAlphanumericLength()));
    }

    public static Post getPostFromResponseForCreatePostByRequest(Post post) {
        Response response = ResponseUtils.getVerifiedResponseForPostRequest(
                Configuration.getPostsPath(), post, HttpStatus.SC_CREATED, ContentType.JSON);
        return ResponseUtils.responseAs(response, Post.class);
    }

    public static void verifyCreatedPostInformationCorrect(Post createdPost, Post post) {
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
