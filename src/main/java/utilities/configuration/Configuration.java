package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import utilities.JsonReader;

@UtilityClass
public class Configuration {
    private static final ISettingsFile configuration =
            JsonReader.getSettingsResourceFile("environment/configuration.json");

    public static String getStartUrl() {
        return configuration.getValue("/startUrl").toString();
    }

    public static String getApiUrl() {
        return configuration.getValue("/apiUrl").toString();
    }
}
