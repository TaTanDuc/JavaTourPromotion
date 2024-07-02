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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/secure")
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "testLogin/login";
    }

    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new Users());
        return "testLogin/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Users user, @NotNull BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "testLogin/login"; // Trả về lại view "register" nếu có lỗi
        }
        userService.save(user);
        userService.setDefaultRole(user.getUsername());
        return "redirect:testLogin/login";
    }
}

