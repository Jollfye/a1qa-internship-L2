package utilities;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@UtilityClass
public class FileReader {
    public static File getResourceFile(String resource) {
        return new File(getResourcePath(resource));
    }

    public static String getResourcePath(final String resourceName) {
        try {
            URL resourceURL = FileReader.class.getClassLoader().getResource(resourceName);
            return Objects.requireNonNull(resourceURL).getPath();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(
                    String.format("Resource file %1$s was not found or cannot be loaded", resourceName), e);
        }
    }

    public static String getCanonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new UncheckedIOException("Error getting canonical path for file: "
                    + file.getAbsolutePath(), e);
        }
    }

    public static String getFileContent(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            throw new UncheckedIOException(
                    String.format("Content of file %1$s can't be read as String", filename), e);
        }
    }
}
