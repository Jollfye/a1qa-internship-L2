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

    public static String getLoginPhone() {
        return vkCredentials.getValue("/loginPhone").toString();
    }

    public static String getLoginPassword() {
        return vkCredentials.getValue("/loginPassword").toString();
    }
}
