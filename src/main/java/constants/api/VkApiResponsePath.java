package constants.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VkApiResponsePath {
    public static final String RESPONSE = "response";
    public static final String FIRST_ITEM_ID = "response[0].id";
    public static final String FIRST_USER_FIRST_NAME = "response[0].first_name";
    public static final String FIRST_USER_LAST_NAME = "response[0].last_name";
    public static final String FIRST_ITEM_OWNER_ID = "response[0].owner_id";
    public static final String POST_ID = "response.post_id";
    public static final String USER_ID = "response.user_id";
    public static final String UPLOAD_URL = "response.upload_url";
    public static final String SERVER = "server";
    public static final String PHOTO = "photo";
    public static final String HASH = "hash";
    public static final String COMMENT_ID = "response.comment_id";
    public static final String ITEMS = "response.items";
}
