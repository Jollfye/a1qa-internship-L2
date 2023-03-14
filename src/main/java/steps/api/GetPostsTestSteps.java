package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.asserts.SoftAssert;
import utilities.api.RequestUtils;
import utilities.api.ResponseUtils;
import utilities.configuration.Configuration;

import java.util.List;

public class GetPostsTestSteps extends BaseSteps {
    private Response response;

    public void getPostsByRequest() {
        response = RequestUtils.sendGetRequest(Configuration.getPostsPath());
    }

    public void verifyResponseStatusCodeAndBodyType() {
        verifyResponseCodeAndContentType(response, HttpStatus.SC_OK, ContentType.JSON);
    }

    public void verifyResponsePostsSortedAscendingById() {
        List<Post> posts = List.of(ResponseUtils.responseAs(response, Post[].class));
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < posts.size() - 1; i++) {
            softAssert.assertTrue(
                    posts.get(i).getId() < posts.get(i + 1).getId(),
                    String.format("Posts are not sorted ascending (by id). Ids: %d, %d",
                            posts.get(i).getId(), posts.get(i + 1).getId()));
        }
        softAssert.assertAll();
    }
}
