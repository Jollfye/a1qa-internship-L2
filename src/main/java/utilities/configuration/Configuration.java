package utilities.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import utilities.JsonReader;

@UtilityClass
public class Configuration {
    private static final ISettingsFile dbConnection =
            JsonReader.getSettingsResourceFile("environment/dbConnection.json");

    public static String getDbUrl() {
        return dbConnection.getValue("/url").toString();
    }

    public static String getDbUser() {
        return dbConnection.getValue("/user").toString();
    }

    public static String getDbPassword() {
        return dbConnection.getValue("/password").toString();
    }
}
