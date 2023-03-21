package utilities.api;

import lombok.experimental.UtilityClass;
import utilities.configuration.TestDataProvider;

@UtilityClass
public class VkApiLikesObjectType {
    public static final String POST = TestDataProvider.getPostType();
    public static final String PHOTO = TestDataProvider.getPhotoType();
    public static final String COMMENT = TestDataProvider.getCommentType();
}
