package tests;

import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.testng.annotations.Test;
import steps.AuthorizationFormSteps;
import steps.WallFormSteps;
import steps.NewsPageSteps;
import utilities.GetModelInstanceUtils;
import utilities.RandomUtils;
import utilities.api.VkApiUtils;
import utilities.configuration.TestDataProvider;

public class VkMyProfileWallPostTest extends BaseTest {
    @Test()
    public void testVkMyProfileWallPost() {
        AuthorizationFormSteps.typeLoginAndClickSignInButton(TestDataProvider.getLogin());
        AuthorizationFormSteps.signInUsingPassword(TestDataProvider.getPassword());
        NewsPageSteps.clickMyProfileLink();
        User user = new User();
        VkApiUtils.setCurrentUserData(user);
        Post post = GetModelInstanceUtils.getNewPostWithRandomMessage(TestDataProvider.getRandomAlphanumericLength());
        VkApiUtils.createUserWallPost(user, post);
        WallFormSteps.verifyPostMessage(post);
        WallFormSteps.verifyPostAuthorName(user, post);
        Photo photo = GetModelInstanceUtils.getNewPhotoWithResource("testdata/images/cherry-blossom.jpg");
        VkApiUtils.uploadPhotoToWallUploadServer(photo,
                VkApiUtils.getUserPhotosWallUploadServer(user));
        VkApiUtils.saveUserWallPhoto(user, photo);
        VkApiUtils.editWallPost(post, RandomUtils.getRandomAlphanumeric(TestDataProvider.getRandomAlphanumericLength()),
                VkApiUtils.getWallPostAttachmentsStringForPhoto(photo));
        WallFormSteps.verifyPostMessage(post);
        WallFormSteps.verifyPostPhotoDisplayed(post);
        Comment comment = GetModelInstanceUtils.getNewCommentWithRandomMessage(TestDataProvider.getRandomAlphanumericLength());
        VkApiUtils.createWallPostComment(post, comment);
        WallFormSteps.clickShowNextCommentLink(post);
        WallFormSteps.verifyCommentAuthorId(user, comment);
        WallFormSteps.clickPostLikeButton(post);
        VkApiUtils.verifyWallPostLikeUserId(user, post);
        VkApiUtils.deleteWallPost(post);
        WallFormSteps.verifyPostDeleted(post);
        VkApiUtils.deletePhoto(photo);
    }
}
