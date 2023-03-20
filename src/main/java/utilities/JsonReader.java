package utilities;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;
import org.apache.commons.text.StringEscapeUtils;

@UtilityClass
public class JsonReader {
    public static ISettingsFile getSettingsResourceFile(String resource) {
        return new JsonSettingsFile(resource);
    }

    public static String unescapeJson(String json) {
        return StringEscapeUtils.unescapeJson(json);
    }
}
