package tests;

import org.testng.annotations.Test;
import steps.ui.MyProfilePageSteps;
import steps.ui.NewsPageSteps;
import steps.ui.SignInVerificationPageSteps;
import steps.ui.WelcomePageSteps;

public class VkUiAndApiTest extends BaseTest {
    private final WelcomePageSteps welcomePageSteps = new WelcomePageSteps();
    private final SignInVerificationPageSteps signInVerificationPageSteps =
            new SignInVerificationPageSteps();
    private final NewsPageSteps newsPageSteps = new NewsPageSteps();
    private final MyProfilePageSteps myProfilePageSteps = new MyProfilePageSteps();

    @Test
    public void testVkUiAndApi() {
        welcomePageSteps.typePhoneAndClickSignInButton();
        signInVerificationPageSteps.signInUsingPassword();
        newsPageSteps.clickMyProfileLink();
    }
}
