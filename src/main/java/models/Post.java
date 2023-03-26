package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    @JsonProperty("body")
    private String postBody;

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPostBody() {
        return postBody;
    }
}
