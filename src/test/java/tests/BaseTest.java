package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.configuration.Configuration;

public abstract class BaseTest {
    @BeforeMethod
    protected void setUp() {
        AqualityServices.getBrowser().goTo(Configuration.getStartUrl());
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
