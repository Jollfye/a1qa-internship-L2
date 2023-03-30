package utilities.api;

import io.restassured.response.Response;

public class ResponseWrapper {
    private final Response response;

    public ResponseWrapper(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getContentType() {
        return response.getContentType();
    }

    public Integer getInt(String path) {
        return response.jsonPath().getInt(path);
    }

    public String getString(String path) {
        return response.jsonPath().getString(path);
    }
}
