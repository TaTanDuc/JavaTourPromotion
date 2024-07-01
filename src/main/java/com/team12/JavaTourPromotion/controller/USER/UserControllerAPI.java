package com.team12.JavaTourPromotion.controller.USER;

import com.team12.JavaTourPromotion.GetVM.UserGetVM;
import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.CommentService;
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
import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserControllerAPI {

    private final static String UPLOADED_FOLDER = "./UserProfileImg";

    private final UserService userService;
    private final CommentService commentService;

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

    @GetMapping("/profile/{username}")
    public ResponseEntity<Optional<UserGetVM>> userProfile(@PathVariable Principal username){
        return ResponseEntity.ok(userService.findUserByUsername(username.getName()));
    }

    @GetMapping("/comment/add")
    public Comments addComment(@RequestParam(value = "destination") Long id, Comments comment, Principal principal){
        return commentService.addComment(id,principal.getName(),comment);
    }
}
