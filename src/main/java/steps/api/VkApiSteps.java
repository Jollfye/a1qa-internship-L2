package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Comment;
import models.Photo;
import models.Post;
import models.User;
import utilities.JsonReader;
import utilities.api.ResponseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VkApiSteps {
    public void setUserIdToCurrentByRequest(User user) {
        String userIdPath = "response[0].id";
        List<String> nonNullPaths = List.of(userIdPath);
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                "/users.get", 200, ContentType.JSON, nonNullPaths);
        user.setId(response.jsonPath().getInt(userIdPath));
    }

    public void createUserWallPostByRequest(User user, Post post) {
        String postIdPath = "response.post_id";
        List<String> nonNullPaths = List.of(postIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", user.getId());
        params.put("message", post.getMessage());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                "/wall.post", params, 200, ContentType.JSON, nonNullPaths);
        post.setId(response.jsonPath().getInt(postIdPath));
        post.setOwnerId(user.getId());
    }

    public String getUserPhotosWallUploadServerByRequest(User user) {
        String userIdPath = "response.user_id";
        String uploadUrlPath = "response.upload_url";
        List<String> nonNullPaths = List.of(userIdPath, uploadUrlPath);
        Response response = ResponseUtils.getVerifiedResponseForGetRequest(
                "/photos.getWallUploadServer",
                200, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, userIdPath, user.getId());
        return response.jsonPath().getString(uploadUrlPath);
    }

    public void uploadPhotoToWallUploadServerByRequest(Photo photo, String uploadUrl) {
        String serverPath = "server";
        String photoPath = "photo";
        String hashPath = "hash";
        List<String> nonNullPaths = List.of(serverPath, photoPath, hashPath);
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithFile(
                uploadUrl, photoPath, photo.getFile(),
                200, ContentType.HTML, nonNullPaths);
        photo.setServer(response.jsonPath().getInt(serverPath));
        photo.setPhoto(JsonReader.unescapeJson(
                response.jsonPath().getString(photoPath)));
        photo.setHash(response.jsonPath().getString(hashPath));
    }

    public void saveUserWallPhotoByRequest(User user, Photo photo) {
        String photoIdPath = "response[0].id";
        String ownerIdPath = "response[0].owner_id";
        List<String> nonNullPaths = List.of(photoIdPath, ownerIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("server", photo.getServer());
        params.put("photo", photo.getPhoto());
        params.put("hash", photo.getHash());
        Response response = ResponseUtils.getVerifiedResponseForGetRequestWithParams(
                "/photos.saveWallPhoto", params,
                200, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, ownerIdPath, user.getId());
        photo.setId(response.jsonPath().getInt(photoIdPath));
        photo.setOwnerId(user.getId());
    }

    public void editWallPostByRequest(Post post, String message, String attachments) {
        String postIdPath = "response.post_id";
        List<String> nonNullPaths = List.of(postIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", post.getOwnerId());
        params.put("post_id", post.getId());
        params.put("message", message);
        params.put("attachments", attachments);
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                "/wall.edit", params,
                200, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, postIdPath, post.getId());
        post.setMessage(message);
        post.setAttachments(attachments);
    }

    public String getWallPostAttachmentsStringForPhoto(Photo photo) {
        return String.format("photo%1$s_%2$s", photo.getOwnerId(), photo.getId());
    }

    public void createWallPostCommentByRequest(Post post, Comment comment) {
        String commentIdPath = "response.comment_id";
        List<String> nonNullPaths = List.of(commentIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", post.getOwnerId());
        params.put("post_id", post.getId());
        params.put("message", comment.getMessage());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                "/wall.createComment", params,
                200, ContentType.JSON, nonNullPaths);
        comment.setId(response.jsonPath().getInt(commentIdPath));
        comment.setPostId(post.getId());
        comment.setOwnerId(post.getOwnerId());
    }

    public void verifyWallPostLikeUserIdByRequest(User user, Post post) {
        String userIdPath = "response.items";
        List<String> nonNullPaths = List.of(userIdPath);
        Map<String, Object> params = new HashMap<>();
        params.put("type", post.getType());
        params.put("owner_id", post.getOwnerId());
        params.put("item_id", post.getId());
        Response response = ResponseUtils.getVerifiedResponseForGetRequestWithParams(
                "/likes.getList", params,
                200, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponseContainsItem(response, userIdPath, user.getId());
    }

    public void deleteWallPostByRequest(Post post) {
        String responsePath = "response";
        List<String> nonNullPaths = List.of(responsePath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", post.getOwnerId());
        params.put("post_id", post.getId());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                "/wall.delete", params,
                200, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, responsePath, 1);
    }

    public void deletePhotoByRequest(Photo photo) {
        String responsePath = "response";
        List<String> nonNullPaths = List.of(responsePath);
        Map<String, Object> params = new HashMap<>();
        params.put("owner_id", photo.getOwnerId());
        params.put("photo_id", photo.getId());
        Response response = ResponseUtils.getVerifiedResponseForPostRequestWithParams(
                "/photos.delete", params,
                200, ContentType.JSON, nonNullPaths);
        ResponseUtils.verifyResponsePathValue(response, responsePath, 1);
    }
}
