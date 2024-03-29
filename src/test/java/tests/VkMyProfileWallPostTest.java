package tests;

import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.api.VkApiSteps;
import steps.ui.AuthorizationFormSteps;
import steps.ui.NewsPageSteps;
import steps.ui.WallFormSteps;
import utilities.GetModelInstanceUtils;
import utilities.RandomUtils;
import utilities.configuration.TestDataProvider;

public class VkMyProfileWallPostTest extends BaseTest {
    private Post post;
    private Photo photo;

    @Test()
    public void testVkMyProfileWallPost() {
        AuthorizationFormSteps.typeLoginAndClickSignInButton(TestDataProvider.getLogin());
        AuthorizationFormSteps.signInUsingPassword(TestDataProvider.getPassword());

        NewsPageSteps.clickMyProfileLink();

        User user = new User();
        VkApiSteps.setCurrentUserData(user);
        post = GetModelInstanceUtils.getNewPostWithRandomMessage(TestDataProvider.getRandomAlphanumericLength());
        VkApiSteps.createUserWallPost(user, post);

        WallFormSteps.verifyPostMessage(post);
        WallFormSteps.verifyPostAuthorName(user, post);

        photo = GetModelInstanceUtils.getNewPhotoWithResource("testdata/images/cherry-blossom.jpg");
        VkApiSteps.uploadPhotoToWallUploadServer(photo,
                VkApiSteps.getUserPhotosWallUploadServer(user));
        VkApiSteps.saveUserWallPhoto(user, photo);
        VkApiSteps.editWallPost(post, RandomUtils.getRandomAlphanumeric(TestDataProvider.getRandomAlphanumericLength()),
                VkApiSteps.getWallPostAttachmentsStringForPhoto(photo));

        WallFormSteps.verifyPostMessageChanged(post);
        WallFormSteps.verifyPostMessage(post);
        WallFormSteps.verifyPostPhotoDisplayed(post);
        WallFormSteps.verifyPostPhotoSameAsUploaded(post, photo);

        Comment comment = GetModelInstanceUtils.getNewCommentWithRandomMessage(TestDataProvider.getRandomAlphanumericLength());
        VkApiSteps.createWallPostComment(post, comment);

        WallFormSteps.clickShowNextCommentLink(post);
        WallFormSteps.verifyCommentAuthorId(user, comment);

        WallFormSteps.clickPostLikeButton(post);

        VkApiSteps.verifyWallPostLikeUserId(user, post);

        VkApiSteps.deleteWallPost(post);

        WallFormSteps.verifyPostDeleted(post);

        VkApiSteps.deletePhoto(photo);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            VkApiSteps.deleteWallPost(post);
            VkApiSteps.deletePhoto(photo);
        }
        super.tearDown();
    }
}
