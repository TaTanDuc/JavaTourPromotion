package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Cities;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.*;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users/user-list";
    }
    // For adding a new product
    @GetMapping("/users/add")
    public String showAddForm(Model model) {
        model.addAttribute("users", new Users());
        return "/users/add-user";
    }
    // Process the form for adding a new product
    @PostMapping("/users/add")
    public String addUser(@Valid Users user, BindingResult result) {
        if (result.hasErrors()) {
            return "/users/add-user";
        }

        userService.addUser(user);
        return "redirect:/users";
    }
    // For editing a product
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Users user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid userID:" + id));
        model.addAttribute("users", user);
        return "/users/update-user";
    }
    // Process the form for updating a product
    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid Users user,
                             BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id); // set id to keep it in the form in case of errors

            return "/users/update-user";
        }
        userService.updateUser(user);
        return "redirect:/users";




    }

}
