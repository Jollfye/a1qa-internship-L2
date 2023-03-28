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
    private static final ISettingsFile dbTablesData =
            JsonReader.getSettingsResourceFile("testdata/db/dbTablesData.json");
    private static final ISettingsFile processTestDataTestData =
            JsonReader.getSettingsResourceFile("testdata/db/processTestDataTestData.json");

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

    public static String getProjectName() {
        return dbTablesData.getValue("/projectName").toString();
    }

    public static String getSessionKey() {
        return dbTablesData.getValue("/sessionKey").toString();
    }

    public static Long getBuildNumber() {
        return Long.valueOf(dbTablesData.getValue("/buildNumber").toString());
    }

    public static String getAuthorName() {
        return dbTablesData.getValue("/authorName").toString();
    }

    public static String getAuthorLogin() {
        return dbTablesData.getValue("/authorLogin").toString();
    }

    public static String getAuthorEmail() {
        return dbTablesData.getValue("/authorEmail").toString();
    }

    public static String getEnv() {
        return dbTablesData.getValue("/env").toString();
    }

    public static String getBrowser() {
        return dbTablesData.getValue("/browser").toString();
    }

    public static int getTestsSelectLimit() {
        return (int) processTestDataTestData.getValue("/testsSelectLimit");
    }

    public static int getMaxWaitSeconds() {
        return (int) processTestDataTestData.getValue("/maxWaitSeconds");
    }
}
