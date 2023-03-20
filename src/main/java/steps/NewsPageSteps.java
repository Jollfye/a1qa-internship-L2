package steps;

import org.testng.Assert;
import pages.MyProfilePage;
import pages.NewsPage;

public class NewsPageSteps {
    private final NewsPage newsPage;
    private final MyProfilePage myProfilePage;

    public NewsPageSteps() {
        newsPage = new NewsPage();
        myProfilePage = new MyProfilePage();
    }

    public void clickMyProfileLink() {
        newsPage.clickMyProfileLink();
        Assert.assertTrue(myProfilePage.state().waitForDisplayed(),
                myProfilePage.getName() + " is not open");
    }
}
