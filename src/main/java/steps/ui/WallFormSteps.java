package steps.ui;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.visualization.IImageComparator;
import aquality.selenium.core.visualization.ImageFunctions;
import lombok.experimental.UtilityClass;
import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.testng.Assert;
import pages.WallForm;
import utilities.configuration.TestDataProvider;

@UtilityClass
public class WallFormSteps {
    private static final WallForm wallForm = new WallForm();

    public static void verifyPostMessage(Post post) {
        Assert.assertEquals(wallForm.getPostText(post),
                post.getMessage(), "Post message is not correct");
    }

    public static void verifyPostMessageChanged(Post post) {
        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() ->
                        !wallForm.getPostText(post).equals(post.getPreviousMessage())),
                "Post message is not changed");
    }

    public static void verifyPostAuthorName(User user, Post post) {
        String authorName = wallForm.getPostAuthorName(post);
        Assert.assertTrue(authorName.contains(user.getFirstName()),
                String.format("Post author name '%1$s' does not contain first name '%2$s'",
                        authorName, user.getFirstName()));
        Assert.assertTrue(authorName.contains(user.getLastName()),
                String.format("Post author name '%1$s' does not contain last name '%2$s'",
                        authorName, user.getLastName()));
    }

    public static void verifyPostPhotoDisplayed(Post post) {
        Assert.assertTrue(wallForm.postPhotoIsDisplayed(post), "Post photo is not displayed");
    }

    public static void verifyPostPhotoSameAsUploaded(Post post, Photo photo) {
        float percentageDifference = AqualityServices.get(IImageComparator.class).percentageDifference(
                wallForm.getPostPhotoScreenshot(post), ImageFunctions.readImage(photo.getFile()));
        float percentageDifferenceThreshold = TestDataProvider.getPhotoPercentageDifferenceThreshold();
        Assert.assertTrue(percentageDifference <= percentageDifferenceThreshold,
                String.format("Post photo is not the same as uploaded. Expected difference is less than %1$s, but actual is %2$s",
                        percentageDifferenceThreshold, percentageDifference));
        AqualityServices.getLogger().info(String.format("Post photo is the same as uploaded. Difference is %1$s, threshold is %2$s",
                percentageDifference, percentageDifferenceThreshold));
    }

    public static void clickShowNextCommentLink(Post post) {
        wallForm.clickShowNextCommentLink(post);
    }

    public static void verifyCommentAuthorId(User user, Comment comment) {
        Assert.assertEquals(wallForm.getCommentAuthorId(comment),
                user.getId(), "Comment author id is not correct");
    }

    public static void clickPostLikeButton(Post post) {
        wallForm.clickPostLikeButton(post);
    }

    public static void verifyPostDeleted(Post post) {
        Assert.assertTrue(wallForm.postIsNotDisplayed(post), "Post is not deleted");
    }
}
