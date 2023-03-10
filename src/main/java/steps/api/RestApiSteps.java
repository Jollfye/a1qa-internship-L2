package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import org.testng.asserts.SoftAssert;
import utilities.RequestSpecifications;

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
        posts.stream().limit(3).forEach(System.out::println);
    }
}
