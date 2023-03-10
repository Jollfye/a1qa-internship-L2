package tests.api;

import org.testng.annotations.Test;
import steps.api.RestApiSteps;

public class RestApiTest {
    private final RestApiSteps restApiSteps = new RestApiSteps();

    @Test
    public void testRestApi() {
        restApiSteps.getPosts();
    }
}
