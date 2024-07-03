package com.team12.JavaTourPromotion.controller.USER;

import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
@RequestMapping("/") // Đường dẫn gốc mà controller này sẽ xử lý.
@RequiredArgsConstructor // Lombok tự động tạo constructor có tham số cho các trường final.
public class UserController {
    private final UserService userService; // Dịch vụ quản lý người dùng
    
    @GetMapping("/login") // Xử lý GET request cho "/login"
    public String login() {
        return "Login/login"; // Trả về view "login" cho người dùng
    }

    @GetMapping("/register") // Xử lý GET request cho "/register"
    public String register(@NotNull Model model) {
        model.addAttribute("user", new Users()); // Thêm một đối tượng User mới vào model
        return "Login/register"; // Trả về view "register"
    }
    @PostMapping("/register") // Xử lý POST request cho "/register"
    public String register(@Valid @ModelAttribute("user") Users user, // Validate đối tượng User
                           @NotNull BindingResult bindingResult,
                           @RequestParam(name = "avatarImage", required = false) MultipartFile avatarImage,// Kết quả của quá trình validate
                           Model model) {
        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
            var errors = bindingResult.getAllErrors() // Lấy tất cả lỗi
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new); // Chuyển các lỗi thành mảng String
            model.addAttribute("errors", errors); // Thêm lỗi vào model
            return "Login/register"; // Trả về lại view "register" nếu có lỗi
        }
        try {
            if (avatarImage != null && !avatarImage.isEmpty()) {
                byte[] bytes = avatarImage.getBytes();
                String fileName = StringUtils.cleanPath(avatarImage.getOriginalFilename());
                user.setProfileImgPath("/images/" + fileName); // Lưu đường dẫn ảnh đại diện
                Path path = Paths.get("src/main/resources/static/images/userprofile" + fileName);
                Files.write(path, bytes);
            } else {
                // Nếu không có ảnh đại diện, gán ảnh ẩn danh
                String defaultAvatar = "/images/userprofile/anonymous.png"; // Đường dẫn ảnh ẩn danh
                user.setProfileImgPath(defaultAvatar); // Gán đường dẫn ảnh ẩn danh cho user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.save(user); // Lưu người dùng vào cơ sở dữ liệu
        userService.setDefaultRole(user.getUsername()); // Gán vai trò mặc định cho người dùng
        return "redirect:/login"; // Chuyển hướng người dùng tới trang "login"
    }
}
