package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import utilities.JsonReader;

@UtilityClass
public class TestDataProvider {
    private static final ISettingsFile vkCredentials =
            JsonReader.getSettingsResourceFile("testdata/vkCredentials.json");
    private static final ISettingsFile vkMyProfileWallPostTestData =
            JsonReader.getSettingsResourceFile("testdata/vkMyProfileWallPostTestData.json");

    public static String getLogin() {
        return vkCredentials.getValue("/login").toString();
    }

    public static String getPassword() {
        return vkCredentials.getValue("/password").toString();
    }

    public static String getAccessToken() {
        return vkCredentials.getValue("/access_token").toString();
    }

    public static int getRandomAlphanumericLength() {
        return (int) vkMyProfileWallPostTestData.getValue("/randomAlphanumericLength");
    }

    public static Float getPhotoPercentageDifferenceThreshold() {
        return Float.valueOf(vkMyProfileWallPostTestData.getValue("/photoPercentageDifferenceThreshold").toString());
    }
}
