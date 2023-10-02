package steps.ui;

import lombok.experimental.UtilityClass;
import org.testng.Assert;
import pages.NewsPage;
import pages.SignInVerificationPage;
import pages.WelcomePage;

@UtilityClass
public class AuthorizationFormSteps {
    private static final WelcomePage welcomePage = new WelcomePage();
    private static final SignInVerificationPage signInVerificationPage = new SignInVerificationPage();
    private static final NewsPage newsPage = new NewsPage();

    public static void typeLoginAndClickSignInButton(String login) {
        welcomePage.typeLogin(login);
        welcomePage.clickSignInButton();
        Assert.assertTrue(signInVerificationPage.state().waitForDisplayed(),
                signInVerificationPage.getName() + " is not open");
    }

    public static void signInUsingPassword(String password) {
        signInVerificationPage.usePasswordVerificationMethod();
        signInVerificationPage.typePassword(password);
        signInVerificationPage.clickContinueButton();
        Assert.assertTrue(newsPage.state().waitForDisplayed(),
                newsPage.getName() + " is not open");
    }
}
