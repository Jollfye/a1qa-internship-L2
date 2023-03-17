package steps.ui;

import pages.SignInVerificationPage;
import utilities.configuration.TestDataProvider;

public class SignInVerificationPageSteps {
    private final SignInVerificationPage signInVerificationPage;

    public SignInVerificationPageSteps() {
        signInVerificationPage = new SignInVerificationPage();
    }

    public void signInUsingPassword() {
        signInVerificationPage.clickUsePasswordButton();
        signInVerificationPage.typePassword(TestDataProvider.getPassword());
        signInVerificationPage.clickContinueButton();
    }
}
