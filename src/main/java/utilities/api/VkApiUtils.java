package utilities.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import org.apache.hc.core5.http.HttpStatus;
import utilities.JsonReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class VkApiUtils {
    public static void setUserIdToCurrentByRequest(User user) {
        String userIdPath = "response[0].id";
        List<String> nonNullPaths = List.of(userIdPath);
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                RequestSpecifications.VkApiCommonGiven(), "/users.get",
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        user.setId(response.jsonPath().getInt(userIdPath));
    }

    public static void createUserWallPostByRequest(User user, Post post) {
        String postIdPath = "response.post_id";
        List<String> nonNullPaths = List.of(postIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", user.getId());
        params.put("message", post.getMessage());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/wall.post", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        post.setId(response.jsonPath().getInt(postIdPath));
        post.setOwnerId(user.getId());
    }

    public static String getUserPhotosWallUploadServerByRequest(User user) {
        String userIdPath = "response.user_id";
        String uploadUrlPath = "response.upload_url";
        List<String> nonNullPaths = List.of(userIdPath, uploadUrlPath);
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                RequestSpecifications.VkApiCommonGiven(), "/photos.getWallUploadServer",
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, userIdPath, user.getId());
        return response.jsonPath().getString(uploadUrlPath);
    }

    public static void uploadPhotoToWallUploadServerByRequest(Photo photo, String uploadUrl) {
        String serverPath = "server";
        String photoPath = "photo";
        String hashPath = "hash";
        List<String> nonNullPaths = List.of(serverPath, photoPath, hashPath);
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithFile(
                RequestSpecifications.VkApiCommonGiven(), uploadUrl, photoPath, photo.getFile(),
                HttpStatus.SC_OK, ContentType.HTML, nonNullPaths);
        photo.setServer(response.jsonPath().getInt(serverPath));
        photo.setPhoto(JsonReader.unescapeJson(
                response.jsonPath().getString(photoPath)));
        photo.setHash(response.jsonPath().getString(hashPath));
    }

    public static void saveUserWallPhotoByRequest(User user, Photo photo) {
        String photoIdPath = "response[0].id";
        String ownerIdPath = "response[0].owner_id";
        List<String> nonNullPaths = List.of(photoIdPath, ownerIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("server", photo.getServer());
        params.put("photo", photo.getPhoto());
        params.put("hash", photo.getHash());
        Response response = ResponseUtils.getVerifiedResponseForGetRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/photos.saveWallPhoto", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, ownerIdPath, user.getId());
        photo.setId(response.jsonPath().getInt(photoIdPath));
        photo.setOwnerId(user.getId());
    }

    public static void editWallPostByRequest(Post post, String message, String attachments) {
        String postIdPath = "response.post_id";
        List<String> nonNullPaths = List.of(postIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", post.getOwnerId());
        params.put("post_id", post.getId());
        params.put("message", message);
        params.put("attachments", attachments);
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/wall.edit", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, postIdPath, post.getId());
        post.setMessage(message);
        post.setAttachments(attachments);
    }

    public static String getWallPostAttachmentsStringForPhoto(Photo photo) {
        return String.format("photo%1$s_%2$s", photo.getOwnerId(), photo.getId());
    }

    public static void createWallPostCommentByRequest(Post post, Comment comment) {
        String commentIdPath = "response.comment_id";
        List<String> nonNullPaths = List.of(commentIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", post.getOwnerId());
        params.put("post_id", post.getId());
        params.put("message", comment.getMessage());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/wall.createComment", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        comment.setId(response.jsonPath().getInt(commentIdPath));
        comment.setPostId(post.getId());
        comment.setOwnerId(post.getOwnerId());
    }

    public static void verifyWallPostLikeUserIdByRequest(User user, Post post) {
        String userIdPath = "response.items";
        List<String> nonNullPaths = List.of(userIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("type", post.getType());
        params.put("owner_id", post.getOwnerId());
        params.put("item_id", post.getId());
        Response response = ResponseUtils.getVerifiedResponseForGetRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/likes.getList", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponseContainsItem(response, userIdPath, user.getId());
    }

    public static void deleteWallPostByRequest(Post post) {
        String responsePath = "response";
        List<String> nonNullPaths = List.of(responsePath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", post.getOwnerId());
        params.put("post_id", post.getId());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/wall.delete", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, responsePath, 1);
    }

    public static void deletePhotoByRequest(Photo photo) {
        String responsePath = "response";
        List<String> nonNullPaths = List.of(responsePath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", photo.getOwnerId());
        params.put("photo_id", photo.getId());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                RequestSpecifications.VkApiCommonGiven(), "/photos.delete", params,
                HttpStatus.SC_OK, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, responsePath, 1);
    }
}
