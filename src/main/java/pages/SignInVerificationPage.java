package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SignInVerificationPage extends Form {
    private final IButton usePasswordButton = getElementFactory().getButton(
            By.xpath("//button[contains(@class,'vkc__Bottom__switchToPassword')]/span"),
            "Sign in using password button");
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(
            By.name("password"),
            "Password text box");
    private final IButton continueButton = getElementFactory().getButton(
            By.xpath("//button[@type='submit']"),
            "Continue button");

    public SignInVerificationPage() {
        super(By.id("otp"),
                "Sign in 2-step verification page");
    }

    public void clickUsePasswordButton() {
        usePasswordButton.click();
    }

    public void typePassword(String password) {
        passwordTextBox.clearAndType(password);
    }

    public void clickContinueButton() {
        continueButton.state().waitForClickable();
        continueButton.clickAndWait();
    }
}
