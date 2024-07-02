package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/security")
public class SecureAPI {

    private final static String UPLOADED_FOLDER = "./UserProfileImg";

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
//        if (file != null && !file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                java.nio.file.Path path = java.nio.file.Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//                java.nio.file.Files.write(path, bytes);
//                user.setProfileImgPath("/UserImage/" + file.getOriginalFilename());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            user.setProfileImgPath("/UserImage/anonymous.png");
//        }
        userService.addUser(user);
        userService.setDefaultRole(user.getUsername());
        return ResponseEntity.ok("Register successful");
    }

    @PostMapping("/login/{username}&{password}")
    public ResponseEntity<String> login(@PathVariable(value = "username") String username,
                                        @PathVariable(value = "password") String password) {
        if(userService.checkLogin(username,password))
            return ResponseEntity.ok("Login successful!");
        else
            return ResponseEntity.notFound().build();
    }
}
