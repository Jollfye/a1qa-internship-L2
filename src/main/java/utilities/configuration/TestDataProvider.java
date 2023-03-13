package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import models.user.User;
import utilities.FileReader;
import utilities.JsonReader;

@UtilityClass
public class TestDataProvider {
    private static final ISettingsFile dataSettingsResourceFile =
            JsonReader.getSettingsResourceFile("RestApiTestData.json");
    private static final String userResourceFileContent = FileReader.getFileContent(
            FileReader.getCanonicalPath(
                    FileReader.getResourceFile("RestApiTestUser.json")));

    public static int getPostId() {
        return (int) dataSettingsResourceFile.getValue("/postId");
    }

    public static int getPostUserId() {
        return (int) dataSettingsResourceFile.getValue("/postUserId");
    }

    public static int getPostIdWithResponseBodyEmpty() {
        return (int) dataSettingsResourceFile.getValue("/postIdWithResponseBodyEmpty");
    }

    public static int getPostRequestUserId() {
        return (int) dataSettingsResourceFile.getValue("/postRequestUserId");
    }

    public static int getRandomAlphanumericLength() {
        return (int) dataSettingsResourceFile.getValue("/randomAlphanumericLength");
    }

    public static User getTestUser() {
        return JsonReader.getValueTypeFromJson(userResourceFileContent, User.class);
    }

    public static int getTestUserId() {
        return (int) dataSettingsResourceFile.getValue("/testUserId");
    }
}
