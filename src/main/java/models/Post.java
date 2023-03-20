package models;

import lombok.Data;

@Data
public class Post {
    private Integer id;
    private Integer ownerId;
    private String message;
    private String attachments;
    private String type = "post";
}
