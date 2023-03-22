package steps;

import lombok.experimental.UtilityClass;
import org.testng.Assert;
import pages.WallForm;
import pages.NewsPage;

@UtilityClass
public class NewsPageSteps {
    private static final NewsPage newsPage = new NewsPage();
    private static final WallForm wallForm = new WallForm();

    public static void clickMyProfileLink() {
        newsPage.clickMyProfileLink();
        Assert.assertTrue(wallForm.state().waitForDisplayed(),
                wallForm.getName() + " is not open");
    }
}
