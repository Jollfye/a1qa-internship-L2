package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {
    private final ILink myProfileLink = getElementFactory().getLink(
            By.xpath("(//a[@class='LeftMenu__itemLink'])[1]"),
            "My profile link");

    public NewsPage() {
        super(By.xpath("(//a[contains(@id,'feed_story')])[1]"), "News page");
    }

    public void clickMyProfileLink() {
        myProfileLink.clickAndWait();
    }
}
