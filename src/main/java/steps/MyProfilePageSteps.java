package steps;

import models.Comment;
import models.Post;
import models.User;
import org.testng.Assert;
import pages.MyProfilePage;

public class MyProfilePageSteps {
    private final MyProfilePage myProfilePage;

    public MyProfilePageSteps() {
        myProfilePage = new MyProfilePage();
    }

    public void verifyPostMessage(Post post) {
        Assert.assertEquals(myProfilePage.getPostText(post),
                post.getMessage(), "Post message is not correct");
    }

    public void verifyPostPhotoDisplayed(Post post) {
        Assert.assertTrue(myProfilePage.getPostPhotoContainerByAttachments(post)
                .state().waitForDisplayed(), "Post photo is not displayed");
    }

    public void clickShowNextCommentLink(Post post) {
        myProfilePage.clickShowNextCommentLink(post);
    }

    public void verifyCommentAuthorUserId(User user, Comment comment) {
        Assert.assertEquals(myProfilePage.getCommentAuthorUserId(comment),
                user.getId(), "Comment author user id is not correct");
    }

    public void clickPostLikeButton(Post post) {
        myProfilePage.clickPostLikeButton(post);
    }
}
