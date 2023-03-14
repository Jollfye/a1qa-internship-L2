package tests.api;

import org.testng.annotations.Test;
import steps.api.CreatePostTestSteps;

public class CreatePostTest {
    private final CreatePostTestSteps createPostTestSteps = new CreatePostTestSteps();

    @Test
    public void testCreatePost() {
        createPostTestSteps.createPostByRequest();
        createPostTestSteps.verifyResponseStatusCodeAndBodyType();
        createPostTestSteps.verifyResponsePostInformationCorrect();
    }
}
