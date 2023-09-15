package enums;

import utilities.FileReader;

public enum JS {
    WAIT_FOR_IMAGE_TO_LOAD("waitForImageToLoad.js");

    private final String filename;

    JS(String filename) {
        this.filename = filename;
    }

    public String getScript() {
        return FileReader.getResourceFileContent("js/" + filename);
    }
}
