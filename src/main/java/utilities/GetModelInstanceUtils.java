package utilities;

import models.Comment;
import models.Photo;
import models.Post;
import utilities.api.VkApiLikesObjectType;

public class GetModelInstanceUtils {
    public static Post getNewPostWithRandomMessage(int length) {
        Post post = new Post();
        post.setType(VkApiLikesObjectType.POST);
        post.setMessage(RandomUtils.getRandomAlphanumeric(length));
        return post;
    }

    public static Photo getNewPhotoWithResource(String resource) {
        Photo photo = new Photo();
        photo.setType(VkApiLikesObjectType.PHOTO);
        photo.setFile(FileReader.getResourceFile(resource));
        return photo;
    }

    public static Comment getNewCommentWithRandomMessage(int length) {
        Comment comment = new Comment();
        comment.setType(VkApiLikesObjectType.COMMENT);
        comment.setMessage(RandomUtils.getRandomAlphanumeric(length));
        return comment;
    }
}
