package steps.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import models.user.User;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.RandomUtils;
import utilities.RequestSpecifications;
import utilities.configuration.TestDataProvider;

import java.util.List;

public class RestApiSteps {
    public void getPosts() {
        Response response = RequestSpecifications.commonGiven().when().get("/posts");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);
        List<Post> posts = List.of(response.as(Post[].class));
        SoftAssert softAssert = new SoftAssert();
        posts.stream()
                .limit(posts.size() - 1)
                .forEach(post -> softAssert.assertTrue(post.getId() < post.getId() + 1,
                        String.format("Posts are not sorted ascending (by id). Ids: %d, %d",
                                post.getId(), post.getId() + 1)));
        softAssert.assertAll();
    }

    public void getPostById(int id) {
        Response response = RequestSpecifications.commonGiven().when().get("/posts/" + id);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);
        Post post = response.as(Post.class);
        Assert.assertEquals(post.getUserId(), TestDataProvider.getPostUserId(),
                "User ids are not equal");
        Assert.assertEquals(post.getId(), TestDataProvider.getPostId(),
                "Post ids are not equal");
        Assert.assertFalse(post.getTitle().isEmpty(),
                "Post title is empty");
        Assert.assertFalse(post.getPostBody().isEmpty(),
                "Post body is empty");
    }

    public void getPostByIdWithResponseBodyEmpty(int id) {
        Response response = RequestSpecifications.commonGiven().when().get("/posts/" + id);
        response.then().assertThat().statusCode(404);
        response.then().assertThat().contentType(ContentType.JSON);
        JsonNode bodyJson = response.getBody().as(JsonNode.class);
        Assert.assertTrue(bodyJson.isEmpty(),
                "Response body is not empty. Body: " + bodyJson);
    }

    public void postWithUserId(int id) {
        Post post = new Post()
                .userId(id)
                .postBody(RandomUtils.getRandomAlphanumeric(
                        TestDataProvider.getRandomAlphanumericLength()))
                .title(RandomUtils.getRandomAlphanumeric(
                        TestDataProvider.getRandomAlphanumericLength()));
        Response response = RequestSpecifications.commonGiven()
                .contentType(ContentType.JSON).body(post)
                .when().post("/posts");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().contentType(ContentType.JSON);
        Post createdPost = response.as(Post.class);
        Assert.assertEquals(createdPost.getTitle(), post.getTitle(),
                "Titles are not equal");
        Assert.assertEquals(createdPost.getPostBody(), post.getPostBody(),
                "Post bodies are not equal");
        Assert.assertEquals(createdPost.getUserId(), post.getUserId(),
                "User ids are not equal");
        Assert.assertNotNull(createdPost.getId(),
                "Post id is null");
    }

    public void getUsers() {
        Response response = RequestSpecifications.commonGiven().when().get("/users");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);
        List<User> users = List.of(response.as(User[].class));
        User user = users.stream()
                .filter(u -> u.getId().equals(TestDataProvider.getTestUserId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Test user is not found"));
        Assert.assertEquals(user, TestDataProvider.getTestUser(), "Users are not equal");
    }

    public void getUserById(int id) {
        Response response = RequestSpecifications.commonGiven().when().get("/users/" + id);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);
        User user = response.as(User.class);
        Assert.assertEquals(user, TestDataProvider.getTestUser(), "Users are not equal");
    }
}
