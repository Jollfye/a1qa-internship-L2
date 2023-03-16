package steps.ui;

import pages.NewsPage;

public class NewsPageSteps {
    private final NewsPage newsPage;

    public NewsPageSteps() {
        newsPage = new NewsPage();
    }

    public void clickMyProfileLink() {
        newsPage.clickMyProfileLink();
    }
}
