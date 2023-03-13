package tests.api;

import org.testng.annotations.Test;
import steps.api.RestApiSteps;
import utilities.configuration.TestDataProvider;

public class RestApiTest {
    private final RestApiSteps restApiSteps = new RestApiSteps();

    @Test
    public void testRestApi() {
        restApiSteps.getPosts();
        restApiSteps.getPostById(TestDataProvider.getPostId());
        restApiSteps.getPostByIdWithResponseBodyEmpty(TestDataProvider.getPostIdWithResponseBodyEmpty());
        restApiSteps.postWithUserId(TestDataProvider.getPostRequestUserId());
        restApiSteps.getUsers();
        restApiSteps.getUserById(TestDataProvider.getTestUserId());
    }
}
