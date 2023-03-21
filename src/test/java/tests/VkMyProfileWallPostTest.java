package tests;

import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.testng.annotations.Test;
import steps.MyProfilePageSteps;
import steps.NewsPageSteps;
import steps.SignInVerificationPageSteps;
import steps.WelcomePageSteps;
import utilities.GetModelInstanceUtils;
import utilities.RandomUtils;
import utilities.api.VkApiUtils;
import utilities.configuration.TestDataProvider;

public class VkMyProfileWallPostTest extends BaseTest {
    private final WelcomePageSteps welcomePageSteps = new WelcomePageSteps();
    private final SignInVerificationPageSteps signInVerificationPageSteps =
            new SignInVerificationPageSteps();
    private final NewsPageSteps newsPageSteps = new NewsPageSteps();
    private final MyProfilePageSteps myProfilePageSteps = new MyProfilePageSteps();

    @Test(invocationCount = 3)
    public void testVkMyProfileWallPost() {
        welcomePageSteps.typePhoneAndClickSignInButton();
        signInVerificationPageSteps.signInUsingPassword();
        newsPageSteps.clickMyProfileLink();
        User user = new User();
        VkApiUtils.setUserIdToCurrentByRequest(user);
        Post post = GetModelInstanceUtils.getNewPostWithRandomMessage(
                TestDataProvider.getRandomAlphanumericLength());
        VkApiUtils.createUserWallPostByRequest(user, post);
        myProfilePageSteps.verifyPostMessage(post);
        Photo photo = GetModelInstanceUtils.getNewPhotoWithResource(
                "testdata/images/cherry-blossom.jpg");
        VkApiUtils.uploadPhotoToWallUploadServerByRequest(photo,
                VkApiUtils.getUserPhotosWallUploadServerByRequest(user));
        VkApiUtils.saveUserWallPhotoByRequest(user, photo);
        VkApiUtils.editWallPostByRequest(post,
                RandomUtils.getRandomAlphanumeric(
                        TestDataProvider.getRandomAlphanumericLength()),
                VkApiUtils.getWallPostAttachmentsStringForPhoto(photo));
        myProfilePageSteps.verifyPostMessage(post);
        myProfilePageSteps.verifyPostPhotoDisplayed(post);
        Comment comment = GetModelInstanceUtils.getNewCommentWithRandomMessage(
                TestDataProvider.getRandomAlphanumericLength());
        VkApiUtils.createWallPostCommentByRequest(post, comment);
        myProfilePageSteps.clickShowNextCommentLink(post);
        myProfilePageSteps.verifyCommentAuthorUserId(user, comment);
        myProfilePageSteps.clickPostLikeButton(post);
        VkApiUtils.verifyWallPostLikeUserIdByRequest(user, post);
        VkApiUtils.deleteWallPostByRequest(post);
        VkApiUtils.deletePhotoByRequest(photo);
    }
}
