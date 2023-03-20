package tests;

import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.testng.annotations.Test;
import steps.api.VkApiSteps;
import steps.ui.MyProfilePageSteps;
import steps.ui.NewsPageSteps;
import steps.ui.SignInVerificationPageSteps;
import steps.ui.WelcomePageSteps;
import utilities.GetModelInstanceUtils;
import utilities.RandomUtils;
import utilities.configuration.TestDataProvider;

public class VkMyProfileWallPostTest extends BaseTest {
    private final WelcomePageSteps welcomePageSteps = new WelcomePageSteps();
    private final SignInVerificationPageSteps signInVerificationPageSteps =
            new SignInVerificationPageSteps();
    private final NewsPageSteps newsPageSteps = new NewsPageSteps();
    private final VkApiSteps vkApiSteps = new VkApiSteps();
    private final MyProfilePageSteps myProfilePageSteps = new MyProfilePageSteps();

    @Test
    public void testVkMyProfileWallPost() {
        welcomePageSteps.typePhoneAndClickSignInButton();
        signInVerificationPageSteps.signInUsingPassword();
        newsPageSteps.clickMyProfileLink();
        User user = new User();
        vkApiSteps.setUserIdToCurrentByRequest(user);
        Post post = GetModelInstanceUtils.getNewPostWithRandomMessage(
                TestDataProvider.getRandomAlphanumericLength());
        vkApiSteps.createUserWallPostByRequest(user, post);
        myProfilePageSteps.verifyPostMessage(post);
        Photo photo = GetModelInstanceUtils.getNewPhotoWithResource(
                "testdata/images/cherry-blossom.jpg");
        vkApiSteps.uploadPhotoToWallUploadServerByRequest(photo,
                vkApiSteps.getUserPhotosWallUploadServerByRequest(user));
        vkApiSteps.saveUserWallPhotoByRequest(user, photo);
        vkApiSteps.editWallPostByRequest(post,
                RandomUtils.getRandomAlphanumeric(
                        TestDataProvider.getRandomAlphanumericLength()),
                vkApiSteps.getWallPostAttachmentsStringForPhoto(photo));
        myProfilePageSteps.verifyPostMessage(post);
        myProfilePageSteps.verifyPostPhotoDisplayed(post);
        Comment comment = GetModelInstanceUtils.getNewCommentWithRandomMessage(
                TestDataProvider.getRandomAlphanumericLength());
        vkApiSteps.createWallPostCommentByRequest(post, comment);
        myProfilePageSteps.clickShowNextCommentLink(post);
        myProfilePageSteps.verifyCommentAuthorUserId(user, comment);
        myProfilePageSteps.clickPostLikeButton(post);
        vkApiSteps.verifyWallPostLikeUserIdByRequest(user, post);
        vkApiSteps.deleteWallPostByRequest(post);
        vkApiSteps.deletePhotoByRequest(photo);
    }
}
