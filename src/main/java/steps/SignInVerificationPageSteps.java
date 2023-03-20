package steps;

import org.testng.Assert;
import pages.NewsPage;
import pages.SignInVerificationPage;
import utilities.configuration.TestDataProvider;

public class SignInVerificationPageSteps {
    private final SignInVerificationPage signInVerificationPage;
    private final NewsPage newsPage;

    public SignInVerificationPageSteps() {
        signInVerificationPage = new SignInVerificationPage();
        newsPage = new NewsPage();
    }

    public void signInUsingPassword() {
        signInVerificationPage.clickUsePasswordButton();
        signInVerificationPage.typePassword(TestDataProvider.getPassword());
        signInVerificationPage.clickContinueButton();
        Assert.assertTrue(newsPage.state().waitForDisplayed(),
                newsPage.getName() + " is not open");
    }
}
