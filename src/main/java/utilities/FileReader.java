package utilities;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@UtilityClass
public class FileReader {
    public static File getResourceFile(String resource) {
        return new File(getResourcePath(resource));
    }

    public static String getResourceFileContent(String resource) {
        Path filePath = Paths.get(getResourcePath(resource));
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new UncheckedIOException(String.format("Failed to read file '%1$s'", filePath), e);
        }
    }

    public static String getResourcePath(final String resourceName) {
        try {
            URL resourceURL = FileReader.class.getClassLoader().getResource(resourceName);
            return Paths.get(Objects.requireNonNull(resourceURL).toURI()).toString();
        } catch (URISyntaxException | NullPointerException e) {
            throw new IllegalArgumentException(
                    String.format("Resource file '%1$s' was not found or cannot be loaded", resourceName), e);
        }
    }
}
