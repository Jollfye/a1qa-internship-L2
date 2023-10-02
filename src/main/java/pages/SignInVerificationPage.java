package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.time.Duration;

public class SignInVerificationPage extends Form {
    private final IButton usePasswordButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'vkc__Bottom__switchToPassword')]/span"), "Sign in using password button");
    private final IButton confirmUsingOtherMethodButton = getElementFactory().getButton(By.xpath("//button[@data-test-id='other-verification-methods']//span[@class='vkuiButton__in']"), "Confirm using other method button");
    private final IButton passwordVerificationMethodButton = getElementFactory().getButton(By.xpath("//div[@data-test-id='verificationMethod_password']"), "Password verification method button");
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(By.name("password"), "Password text box");
    private final IButton continueButton = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Continue button");

    public SignInVerificationPage() {
        super(By.className("vkc__Auth__pageBox"), "Sign in 2-step verification page");
    }

    public void usePasswordVerificationMethod() {
        if (usePasswordButton.state().waitForDisplayed(Duration.ofSeconds(1))) {
            usePasswordButton.click();
        } else if (confirmUsingOtherMethodButton.state().waitForDisplayed()) {
            confirmUsingOtherMethodButton.click();
            clickPasswordVerificationMethodButton();
        }
    }

    private void clickPasswordVerificationMethodButton() {
        passwordVerificationMethodButton.state().waitForDisplayed();
        passwordVerificationMethodButton.click();
    }

    public void typePassword(String password) {
        passwordTextBox.state().waitForDisplayed();
        passwordTextBox.clearAndType(password);
    }

    public void clickContinueButton() {
        continueButton.state().waitForClickable();
        continueButton.clickAndWait();
    }
}
