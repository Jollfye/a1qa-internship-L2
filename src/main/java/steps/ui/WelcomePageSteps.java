package steps.ui;

import pages.WelcomePage;
import utilities.configuration.TestDataProvider;

public class WelcomePageSteps {
    private final WelcomePage welcomePage;

    public WelcomePageSteps() {
        welcomePage = new WelcomePage();
    }

    public void typePhoneAndClickSignInButton() {
        welcomePage.typePhone(TestDataProvider.getLoginPhone());
        welcomePage.clickSignInButton();
    }
}
