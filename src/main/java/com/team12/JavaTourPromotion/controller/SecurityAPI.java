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

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/security")
public class SecurityAPI {

    private final UserService userService;

    @GetMapping("/login")
    public String loginLayout(){
        return "Login/login";
    }

    @GetMapping("/register")
    public String registerLayout(){
        return "Login/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Users user, @NotNull BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "Login/register"; // Trả về lại view "register" nếu có lỗi
        }
        userService.addUser(user);
        userService.setDefaultRole(user.getUsername());
        return "redirect:Login/login";
    }
}
