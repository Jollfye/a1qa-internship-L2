package models;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer postId;
    private Integer ownerId;
    private String message;
    private String type = "comment";
}
