package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.configuration.Configuration;

public abstract class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

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
