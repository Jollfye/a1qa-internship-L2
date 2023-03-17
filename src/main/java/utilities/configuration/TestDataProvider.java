package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import utilities.JsonReader;

@UtilityClass
public class TestDataProvider {
    private static final ISettingsFile vkCredentials =
            JsonReader.getSettingsResourceFile("testdata/vkCredentials.json");
    private static final ISettingsFile vkUiAndApiTestData =
            JsonReader.getSettingsResourceFile("testdata/vkUiAndApiTestData.json");

    public static String getLogin() {
        return vkCredentials.getValue("/login").toString();
    }

    public static String getPassword() {
        return vkCredentials.getValue("/password").toString();
    }

    public static String getAccessToken() {
        return vkCredentials.getValue("/access_token").toString();
    }
}
