package com.team12.JavaTourPromotion.controller;

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
public class UserController {
    public static String UPLOADED_FOLDER = "C:/Users/RON/OneDrive/Desktop/JavaTourPromotion/src/main/resources/static/UserImage/";
    public static String DEFAULT_IMAGE = "C:/Users/RON/OneDrive/Desktop/JavaTourPromotion/src/main/resources/static/UserImage/anonymous.png";

    @Autowired
    private final UserService userService;
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new Users()); // Thêm một đối tượng User mới vào model
        return "users/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Users user, // Validateđối tượng User
                           @NotNull BindingResult bindingResult, // Kết quả của quá trình validate
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
                // Lấy byte từ tệp được tải lên
                byte[] bytes = file.getBytes();
                // Tạo đường dẫn đến thư mục lưu trữ
                java.nio.file.Path path = java.nio.file.Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                // Lưu tệp vào đường dẫn đã tạo
                java.nio.file.Files.write(path, bytes);
                // Lưu URL của hình ảnh vào thuộc tính imageUrl của sản phẩm
                user.setProfileImgPath("/UserImage/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // Set the default anonymous image if no image is uploaded
            user.setProfileImgPath("/UserImage/anonymous.png");
        }
        userService.addUser(user); // Lưu người dùng vào cơ sở dữ liệu
        userService.setDefaultRole(user.getUsername()); // Gán vai trò mặc định cho người dùng
        return "redirect:/login"; // Chuyển hướng người dùng tới trang "login"
    }
    @GetMapping("users/ban/{id}")
    public String banUser(@PathVariable String userName) {
        userService.banUser(userName);
        return "redirect:/Users";
    }
    @GetMapping("users/unban/{id}")
    public String unbanUser(@PathVariable String userName) {
        userService.unbanUser(userName);
        return "redirect:/Users";
    }
}
