package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Cities;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.*;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequiredArgsConstructor
public class UserController {

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
                           Model model) {
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
        userService.addUser(user); // Lưu người dùng vào cơ sở dữ liệu
        userService.setDefaultRole(user.getUsername()); // Gán vai trò mặc định cho người dùng
        return "redirect:/login"; // Chuyển hướng người dùng tới trang "login"
    }

}
