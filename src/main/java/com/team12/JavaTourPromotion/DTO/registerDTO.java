package com.team12.JavaTourPromotion.DTO;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Data
public class registerDTO {
    private String name;
    private String username;
    private String email;
    private String password;
}
