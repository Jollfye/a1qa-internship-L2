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

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;

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
        Assert.assertEquals(authorName, user.getFirstName() + " " + user.getLastName(),
                String.format("Post author name '%1$s' does not equal to '%2$s %3$s'",
                        authorName, user.getFirstName(), user.getLastName()));
    }

    public static void verifyPostPhotoDisplayed(Post post) {
        Assert.assertTrue(wallForm.postPhotoIsDisplayed(post), "Post photo is not displayed");
    }

    public static void verifyPostPhotoSameAsUploaded(Post post, Photo photo) {
        Image postPhotoScreenshot = wallForm.getPostPhotoScreenshot(post);
        Image uploadedPhoto = ImageFunctions.readImage(photo.getFile());
        float percentageDifference = AqualityServices.get(IImageComparator.class).percentageDifference(
                postPhotoScreenshot, uploadedPhoto);
        float percentageDifferenceThreshold = TestDataProvider.getPhotoPercentageDifferenceThreshold();
        boolean condition = percentageDifference <= percentageDifferenceThreshold;
        if (!condition) {
            String errorMessage = String.format("Post photo is not the same as uploaded. Expected difference is less than %1$s, but actual is %2$s",
                    percentageDifferenceThreshold, percentageDifference);
            throw new CustomAssertionError(errorMessage, post, postPhotoScreenshot, uploadedPhoto);
        }
        AqualityServices.getLogger().info(String.format("Post photo is the same as uploaded. Difference is %1$s, threshold is %2$s",
                percentageDifference, percentageDifferenceThreshold));
    }

    private static class CustomAssertionError extends AssertionError {
        public CustomAssertionError(String message, Post post, Image postPhotoScreenshot, Image uploadedPhoto) {
            super(message);
            AqualityServices.getLogger().error(message);
            try {
                File file = Paths.get("./src/main/resources/testdata/images",
                        "post_photo_screenshot_" + post.getId() + ".png").toFile();
                ImageFunctions.save(postPhotoScreenshot, file, "png");
                file = Paths.get("./src/main/resources/testdata/images",
                        "post_photo_" + post.getId() + ".png").toFile();
                ImageFunctions.save(uploadedPhoto, file, "png");
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to save images", e);
            }
        }
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
