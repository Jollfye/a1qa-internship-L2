package models;

import lombok.Data;

import java.io.File;

@Data
public class Photo {
    private Integer id;
    private Integer ownerId;
    private String type;
    private File file;
    private Integer server;
    private String photo;
    private String hash;
}
