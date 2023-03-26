package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import models.user.User;
import utilities.FileReader;
import utilities.JsonReader;

@UtilityClass
public class TestDataProvider {
    private static final ISettingsFile getPostWithIdTestData =
            JsonReader.getSettingsResourceFile("testdata/api/getPostWithIdTestData.json");
    private static final ISettingsFile getResponseForNonExistentPostTestData =
            JsonReader.getSettingsResourceFile("testdata/api/getResponseForNonExistentPostTestData.json");
    private static final ISettingsFile createPostTestData =
            JsonReader.getSettingsResourceFile("testdata/api/createPostTestData.json");
    private static final String testUser = FileReader.getFileContent(
            FileReader.getCanonicalPath(
                    FileReader.getResourceFile("testdata/api/testUser.json")));

    public static int getPostId() {
        return (int) getPostWithIdTestData.getValue("/postId");
    }

    public static int getPostUserId() {
        return (int) getPostWithIdTestData.getValue("/postUserId");
    }

    public static int getNonExistentPostId() {
        return (int) getResponseForNonExistentPostTestData.getValue("/nonExistentPostId");
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
