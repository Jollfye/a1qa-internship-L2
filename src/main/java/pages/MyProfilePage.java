package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.HighlightState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import models.Comment;
import models.Post;
import org.openqa.selenium.By;

public class MyProfilePage extends Form {
    private final ILabel wallPostsContainer = getElementFactory().getLabel(
            By.id("page_wall_posts"),
            "Wall posts container");
    private final By postTextLocator =
            By.xpath(".//div[contains(@class,'wall_post_text')]");
    private final By postShowNextCommentLinkLocator =
            By.xpath(".//span[@class='js-replies_next_label']");
    private final By authorLinkLocator =
            By.xpath(".//a[@class='author']");
    private final By postLikeButtonCounterLocator =
            By.xpath(".//div[contains(@class,'_counter_anim_container')]");

    public MyProfilePage() {
        super(By.className("ProfileIndicatorBadge"), "My profile page");
    }

    public String getPostText(Post post) {
        return getContainerByIds(post.getOwnerId(), post.getId()).findChildElement(
                postTextLocator, "Post text", ElementType.LABEL)
                .getText();
    }

    public ILink getPostPhotoContainerByAttachments(Post post) {
        return getContainerByIds(post.getOwnerId(), post.getId()).findChildElement(
                By.xpath(String.format(".//a[contains(@href,'%1$s')]", post.getAttachments())),
                String.format("Post photo container '%1$s'", post.getAttachments()),
                ElementType.LINK);
    }

    public void clickShowNextCommentLink(Post post) {
        getContainerByIds(post.getOwnerId(), post.getId()).findChildElement(
                postShowNextCommentLinkLocator, "Show next comment link", ElementType.LINK)
                .click();
    }

    public int getCommentAuthorUserId(Comment comment) {
        return Integer.parseInt(getContainerByIds(comment.getOwnerId(), comment.getId())
                .findChildElement(authorLinkLocator, "Author link", ElementType.LINK)
                .getAttribute("data-from-id", HighlightState.HIGHLIGHT));
    }

    public void clickPostLikeButton(Post post) {
        IButton postLikeButton = getContainerByIds(post.getOwnerId(), post.getId())
                .findChildElement(By.xpath(String.format(".//div[contains(" +
                                        "@data-reaction-target-object,'%1$s_%2$s')]",
                                post.getOwnerId(), post.getId())),
                        "Post like button", ElementType.BUTTON);
        postLikeButton.click();
        postLikeButton.findChildElement(postLikeButtonCounterLocator,
                "Post like button counter", ElementType.LABEL)
                .state().waitForDisplayed();
    }

    public ILabel getContainerByIds(int ownerId, int id) {
        return wallPostsContainer.findChildElement(
                By.xpath(String.format(".//div[@data-post-id='%1$s_%2$s']",
                        ownerId, id)),
                String.format("Container '%1$s', owner '%2$s'",
                        id, ownerId),
                ElementType.LABEL);
    }
}
