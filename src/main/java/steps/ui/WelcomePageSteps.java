package steps.ui;

import org.testng.Assert;
import pages.SignInVerificationPage;
import pages.WelcomePage;
import utilities.configuration.TestDataProvider;

public class WelcomePageSteps {
    private final WelcomePage welcomePage;
    private final SignInVerificationPage signInVerificationPage;

    public WelcomePageSteps() {
        welcomePage = new WelcomePage();
        signInVerificationPage = new SignInVerificationPage();
    }

    public void typePhoneAndClickSignInButton() {
        welcomePage.typeLogin(TestDataProvider.getLogin());
        welcomePage.clickSignInButton();
        Assert.assertTrue(signInVerificationPage.state().waitForDisplayed(),
                signInVerificationPage.getName() + " is not open");
    }
}
