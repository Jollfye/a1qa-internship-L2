package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import models.user.User;
import utilities.FileReader;
import utilities.JsonReader;

@UtilityClass
public class TestDataProvider {
    private static final ISettingsFile getPostWithIdTestData =
            JsonReader.getSettingsResourceFile("getPostWithIdTestData.json");
    private static final ISettingsFile getPostWithResponseBodyEmptyTestData =
            JsonReader.getSettingsResourceFile("getPostWithResponseBodyEmptyTestData.json");
    private static final ISettingsFile createPostTestData =
            JsonReader.getSettingsResourceFile("createPostTestData.json");
    private static final String testUser = FileReader.getFileContent(
            FileReader.getCanonicalPath(
                    FileReader.getResourceFile("testUser.json")));

    public static int getPostId() {
        return (int) getPostWithIdTestData.getValue("/postId");
    }

    public static int getPostUserId() {
        return (int) getPostWithIdTestData.getValue("/postUserId");
    }

    public static int getPostIdWithResponseBodyEmpty() {
        return (int) getPostWithResponseBodyEmptyTestData.getValue("/postIdWithResponseBodyEmpty");
    }

    public static int getPostRequestUserId() {
        return (int) createPostTestData.getValue("/postRequestUserId");
    }

    public static int getRandomAlphanumericLength() {
        return (int) createPostTestData.getValue("/randomAlphanumericLength");
    }

    public static int getTestUserId() {
        return getTestUser().getId();
    }

    public static User getTestUser() {
        return JsonReader.getValueTypeFromJson(testUser, User.class);
    }
}
