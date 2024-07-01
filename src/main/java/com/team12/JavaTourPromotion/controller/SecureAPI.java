package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
    public String register(@Valid @ModelAttribute("user") Users user,
                           @NotNull BindingResult bindingResult,
                           Model model, @RequestParam("image") MultipartFile file) {
        if (userService.existsByUsername(user.getUsername()))
        {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        if (userService.existsByEmail(user.getEmail()))
        {
            bindingResult.rejectValue("Email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "users/register"; // Trả về lại view "register" nếu có lỗi
        }
        if (file != null && !file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                java.nio.file.Path path = java.nio.file.Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                java.nio.file.Files.write(path, bytes);
                user.setProfileImgPath("/UserImage/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            user.setProfileImgPath("/UserImage/anonymous.png");
        }
        userService.addUser(user);
        userService.setDefaultRole(user.getUsername());
        return "redirect:/login";
    }
//
//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("user") Users user, @NotNull BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "Login/register"; // Trả về lại view "register" nếu có lỗi
//        }
//        userService.addUser(user);
//        userService.setDefaultRole(user.getUsername());
//        return "redirect:Login/login";
//    }
}
