package tests.api;

import org.testng.annotations.Test;
import steps.api.GetPostWithResponseBodyEmptyTestSteps;

public class GetPostWithResponseBodyEmptyTest {
    private final GetPostWithResponseBodyEmptyTestSteps getPostWithResponseBodyEmptyTestSteps =
            new GetPostWithResponseBodyEmptyTestSteps();

    @Test
    public void testGetPostWithResponseBodyEmpty() {
        getPostWithResponseBodyEmptyTestSteps.getPostWithResponseBodyEmptyByRequest();
        getPostWithResponseBodyEmptyTestSteps.verifyResponseStatusCodeAndBodyType();
        getPostWithResponseBodyEmptyTestSteps.verifyResponseBodyEmpty();
    }
}
