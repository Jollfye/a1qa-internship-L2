package utilities;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileReader {
    public static ISettingsFile getSettingsResourceFile(String resource) {
        return new JsonSettingsFile(resource);
    }
}
