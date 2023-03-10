package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Post {
    private int userId;
    private int id;
    private String title;
    @JsonProperty("body")
    private String postBody;
}
