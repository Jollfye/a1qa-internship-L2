package pages;

import aquality.selenium.core.visualization.ImageFunctions;
import aquality.selenium.elements.HighlightState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import models.Comment;
import models.Post;
import org.openqa.selenium.By;

import java.awt.Image;

public class WallForm extends Form {
    private static final String POSTS_CONTAINER_XPATH = "//div[@id='page_wall_posts']";
    private static final String POST_CONTAINER_XPATH = "//div[@data-post-id='%1$s_%2$s']";
    private static final String POST_TEXT_LABEL_XPATH = "//div[contains(@class,'wall_post_text')]";
    private static final String POST_PHOTO_CONTAINER_XPATH = "//a[contains(@href,'%1$s')]";
    private static final String POST_SHOW_NEXT_COMMENT_LINK_XPATH = "//span[@class='js-replies_next_label']";
    private static final String POST_AUTHOR_LINK_XPATH = "//a[@class='author']";
    private static final String POST_LIKE_BUTTON_XPATH = "//div[contains(@data-reaction-target-object,'%1$s_%2$s')]";
    private static final String POST_LIKE_BUTTON_COUNTER_LABEL_XPATH = "//div[contains(@class,'_counter_anim_container')]";
    private static final String POST_AUTHOR_USER_ID_ATTRIBUTE = "data-from-id";

    public WallForm() {
        super(By.className("ProfileIndicatorBadge"), "My profile page");
    }

    public String getPostText(Post post) {
        return getPostTextLabel(post).getText();
    }

    public String getPostAuthorName(Post post) {
        return getPostAuthorLink(post).getText();
    }

    public boolean postPhotoIsDisplayed(Post post) {
        return getPostPhotoContainer(post).state().waitForDisplayed();
    }

    public Image getPostPhotoScreenshot(Post post) {
        return ImageFunctions.getScreenshotAsImage(getPostPhotoContainer(post).getElement());
    }

    public void clickShowNextCommentLink(Post post) {
        getShowNextCommentLink(post).click();
    }

    public int getCommentAuthorId(Comment comment) {
        return Integer.parseInt(getCommentAuthorLink(comment)
                .getAttribute(POST_AUTHOR_USER_ID_ATTRIBUTE, HighlightState.HIGHLIGHT));
    }

    public void clickPostLikeButton(Post post) {
        getPostLikeButton(post).click();
        getPostLikeButtonCounterLabel(post).state().waitForDisplayed();
    }

    public boolean postIsNotDisplayed(Post post) {
        return getPostContainer(post).state().waitForNotDisplayed();
    }

    private ILabel getPostContainer(Post post) {
        return getElementFactory().getLabel(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH,
                        post.getOwnerId(), post.getId())), "Post container");
    }

    private ILabel getPostTextLabel(Post post) {
        return getElementFactory().getLabel(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH + POST_TEXT_LABEL_XPATH,
                        post.getOwnerId(), post.getId())), "Post text label");
    }

    private ILink getPostAuthorLink(Post post) {
        return getElementFactory().getLink(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH + POST_AUTHOR_LINK_XPATH,
                        post.getOwnerId(), post.getId())), "Post author link");
    }

    private ILink getPostPhotoContainer(Post post) {
        return getElementFactory().getLink(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH,
                        post.getOwnerId(), post.getId())
                        + String.format(POST_PHOTO_CONTAINER_XPATH, post.getAttachments())), "Post photo container");
    }

    private ILink getShowNextCommentLink(Post post) {
        return getElementFactory().getLink(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH + POST_SHOW_NEXT_COMMENT_LINK_XPATH,
                        post.getOwnerId(), post.getId())), "Show next comment link");
    }

    private ILink getCommentAuthorLink(Comment comment) {
        return getElementFactory().getLink(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH + POST_AUTHOR_LINK_XPATH,
                        comment.getOwnerId(), comment.getId())), "Comment author link");
    }

    private IButton getPostLikeButton(Post post) {
        return getElementFactory().getButton(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH + POST_LIKE_BUTTON_XPATH,
                        post.getOwnerId(), post.getId())), "Post like button");
    }

    private ILabel getPostLikeButtonCounterLabel(Post post) {
        return getElementFactory().getLabel(
                By.xpath(String.format(POSTS_CONTAINER_XPATH + POST_CONTAINER_XPATH
                                + POST_LIKE_BUTTON_XPATH + POST_LIKE_BUTTON_COUNTER_LABEL_XPATH,
                        post.getOwnerId(), post.getId())), "Post like button counter label");
    }
}
