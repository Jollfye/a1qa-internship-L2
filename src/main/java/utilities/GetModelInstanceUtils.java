package utilities;

import models.Comment;
import models.Photo;
import models.Post;

public class GetModelInstanceUtils {
    public static Post getNewPostWithRandomMessage(int length) {
        Post post = new Post();
        post.setMessage(RandomUtils.getRandomAlphanumeric(length));
        return post;
    }

    public static Photo getNewPhotoWithResource(String resource) {
        Photo photo = new Photo();
        photo.setFile(FileReader.getResourceFile(resource));
        return photo;
    }

    public static Comment getNewCommentWithRandomMessage(int length) {
        Comment comment = new Comment();
        comment.setMessage(RandomUtils.getRandomAlphanumeric(length));
        return comment;
    }
}
