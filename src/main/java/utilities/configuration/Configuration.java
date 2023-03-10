package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import utilities.FileReader;

@UtilityClass
public class Configuration {
    private static final ISettingsFile configuration =
            FileReader.getSettingsResourceFile("environment/configuration.json");

    public static String getApiUrl() {
        return configuration.getValue("/apiUrl").toString();
    }
}
