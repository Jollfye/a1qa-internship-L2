package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsPage extends Form {
    private final ILink myProfileLink = getElementFactory().getLink(By.xpath("//li[@id='l_pr']/a"), "My profile link");

    public NewsPage() {
        super(By.xpath("(//a[contains(@id,'feed_story')])[1]"), "News page");
    }

    public void clickMyProfileLink() {
        myProfileLink.clickAndWait();
    }
}
