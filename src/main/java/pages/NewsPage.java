package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {
    private final ILink myProfileLink = getElementFactory().getLink(
            By.xpath("(//a[@class='LeftMenu__itemLink'])[1]"),
            "My profile link");

    public NewsPage() {
        super(By.id("feed_rows"), "News page");
    }

    public void clickMyProfileLink() {
        myProfileLink.clickAndWait();
    }
}