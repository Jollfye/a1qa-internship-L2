package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {
    private final ITextBox loginTextBox = getElementFactory().getTextBox(
            By.id("index_email"),
            "Phone or email text box");
    private final IButton signInButton = getElementFactory().getButton(
            By.xpath("//button[contains(@class,'VkIdForm__signInButton')]" +
                    "//span[@class='FlatButton__content']"),
            "Sign in button");

    public WelcomePage() {
        super(By.className("LoginMobilePromo__devices"), "Welcome page");
    }

    public void typeLogin(String login) {
        loginTextBox.clearAndType(login);
    }

    public void clickSignInButton() {
        signInButton.clickAndWait();
    }
}
