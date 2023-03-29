package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.apache.hc.core5.http.HttpStatus;
import utilities.JsonReader;
import utilities.api.RequestSpecifications;
import utilities.api.ResponseUtils;
import utilities.api.constants.VkApiMethodPath;
import utilities.api.constants.VkApiParam;
import utilities.api.constants.VkApiResponsePath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class VkApiSteps {
    public static void setCurrentUserData(User user) {
        String userIdPath = VkApiResponsePath.FIRST_ITEM_ID;
        String firstNamePath = VkApiResponsePath.FIRST_USER_FIRST_NAME;
        String lastNamePath = VkApiResponsePath.FIRST_USER_LAST_NAME;
        List<String> nonNullPaths = List.of(userIdPath, firstNamePath, lastNamePath);
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.USERS_GET,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        user.setId(response.jsonPath().getInt(userIdPath));
        user.setFirstName(response.jsonPath().getString(firstNamePath));
        user.setLastName(response.jsonPath().getString(lastNamePath));
    }

    public static void createUserWallPost(User user, Post post) {
        String postIdPath = VkApiResponsePath.POST_ID;
        List<String> nonNullPaths = List.of(postIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.OWNER_ID, user.getId());
        params.put(VkApiParam.MESSAGE, post.getMessage());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.WALL_POST, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        post.setId(response.jsonPath().getInt(postIdPath));
        post.setOwnerId(user.getId());
    }

    public static String getUserPhotosWallUploadServer(User user) {
        String userIdPath = VkApiResponsePath.USER_ID;
        String uploadUrlPath = VkApiResponsePath.UPLOAD_URL;
        List<String> nonNullPaths = List.of(userIdPath, uploadUrlPath);
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.PHOTOS_GET_WALL_UPLOAD_SERVER,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, userIdPath, user.getId());
        return response.jsonPath().getString(uploadUrlPath);
    }

    public static void uploadPhotoToWallUploadServer(Photo photo, String uploadUrl) {
        String serverPath = VkApiResponsePath.SERVER;
        String photoPath = VkApiResponsePath.PHOTO;
        String hashPath = VkApiResponsePath.HASH;
        List<String> nonNullPaths = List.of(serverPath, photoPath, hashPath);
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithFile(
                RequestSpecifications.VkApiCommonGiven(), uploadUrl, photoPath, photo.getFile(),
                HttpStatus.SC_OK, ContentType.HTML, nonNullPaths);
        photo.setServer(response.jsonPath().getInt(serverPath));
        photo.setPhoto(JsonReader.unescapeJson(
                response.jsonPath().getString(photoPath)));
        photo.setHash(response.jsonPath().getString(hashPath));
    }

    public static void saveUserWallPhoto(User user, Photo photo) {
        String photoIdPath = VkApiResponsePath.FIRST_ITEM_ID;
        String ownerIdPath = VkApiResponsePath.FIRST_ITEM_OWNER_ID;
        List<String> nonNullPaths = List.of(photoIdPath, ownerIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.SERVER, photo.getServer());
        params.put(VkApiParam.PHOTO, photo.getPhoto());
        params.put(VkApiParam.HASH, photo.getHash());
        Response response = ResponseUtils.getVerifiedResponseForGetRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.PHOTOS_SAVE_WALL_PHOTO, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, ownerIdPath, user.getId());
        photo.setId(response.jsonPath().getInt(photoIdPath));
        photo.setOwnerId(user.getId());
    }

    public static void editWallPost(Post post, String message, String attachments) {
        String postIdPath = VkApiResponsePath.POST_ID;
        List<String> nonNullPaths = List.of(postIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.OWNER_ID, post.getOwnerId());
        params.put(VkApiParam.POST_ID, post.getId());
        params.put(VkApiParam.MESSAGE, message);
        params.put(VkApiParam.ATTACHMENTS, attachments);
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.WALL_EDIT, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, postIdPath, post.getId());
        post.setPreviousMessage(post.getMessage());
        post.setMessage(message);
        post.setAttachments(attachments);
    }

    public static String getWallPostAttachmentsStringForPhoto(Photo photo) {
        return String.format(VkApiParam.ATTACHMENTS_PHOTO, photo.getOwnerId(), photo.getId());
    }

    public static void createWallPostComment(Post post, Comment comment) {
        String commentIdPath = VkApiResponsePath.COMMENT_ID;
        List<String> nonNullPaths = List.of(commentIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.OWNER_ID, post.getOwnerId());
        params.put(VkApiParam.POST_ID, post.getId());
        params.put(VkApiParam.MESSAGE, comment.getMessage());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.WALL_CREATE_COMMENT, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        comment.setId(response.jsonPath().getInt(commentIdPath));
        comment.setPostId(post.getId());
        comment.setOwnerId(post.getOwnerId());
    }

    public static void verifyWallPostLikeUserId(User user, Post post) {
        String userIdPath = VkApiResponsePath.ITEMS;
        List<String> nonNullPaths = List.of(userIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.TYPE, post.getType());
        params.put(VkApiParam.OWNER_ID, post.getOwnerId());
        params.put(VkApiParam.ITEM_ID, post.getId());
        Response response = ResponseUtils.getVerifiedResponseForGetRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.LIKES_GET_LIST, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponseContainsItem(response, userIdPath, user.getId());
    }

    public static void deleteWallPost(Post post) {
        String responsePath = VkApiResponsePath.RESPONSE;
        List<String> nonNullPaths = List.of(responsePath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.OWNER_ID, post.getOwnerId());
        params.put(VkApiParam.POST_ID, post.getId());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.WALL_DELETE, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, responsePath, 1);
    }

    public static void deletePhoto(Photo photo) {
        String responsePath = VkApiResponsePath.RESPONSE;
        List<String> nonNullPaths = List.of(responsePath);
        Map<String, Object> params = new HashMap<>();
        params.put(VkApiParam.OWNER_ID, photo.getOwnerId());
        params.put(VkApiParam.PHOTO_ID, photo.getId());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), VkApiMethodPath.PHOTOS_DELETE, params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, responsePath, 1);
    }
}
