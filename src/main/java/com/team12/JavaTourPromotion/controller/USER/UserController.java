package com.team12.JavaTourPromotion.controller.USER;

import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("user") Users user,
//                           @NotNull BindingResult bindingResult,
//                           Model model, @RequestParam("image") MultipartFile file) {
//        if (userService.existsByUsername(user.getUsername()))
//        {
//            bindingResult.rejectValue("username", "error.user", "Username already exists");
//        }
//
//        if (userService.existsByEmail(user.getEmail()))
//        {
//            bindingResult.rejectValue("Email", "error.user", "Email already exists");
//        }
//
//        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "users/register"; // Trả về lại view "register" nếu có lỗi
//        }
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
//            // Set the default anonymous image if no image is uploaded
//            user.setProfileImgPath("/UserImage/anonymous.png");
//        }
//        userService.addUser(user); // Lưu người dùng vào cơ sở dữ liệu
//        userService.setDefaultRole(user.getUsername()); // Gán vai trò mặc định cho người dùng
//        return "redirect:/login"; // Chuyển hướng người dùng tới trang "login"
//    }
//    @GetMapping("users/ban/{id}")
//    public String banUser(@PathVariable String userName) {
//        userService.banUser(userName);
//        return "redirect:/Users";
//    }
//    @GetMapping("users/unban/{id}")
//    public String unbanUser(@PathVariable String userName) {
//        userService.unbanUser(userName);
//        return "redirect:/Users";
//    }
}
