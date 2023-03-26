package utilities;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.UncheckedIOException;

@UtilityClass
public class JsonReader {
    public static ISettingsFile getSettingsResourceFile(String resource) {
        return new JsonSettingsFile(resource);
    }

    public static <T> T getValueTypeFromJson(String content, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new UncheckedIOException(
                    String.format("Error reading value of type %1$s from JSON: %2$s", valueType, content), e);
        }
    }
}
