package com.team12.JavaTourPromotion.controller.ADMIN;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControllerPage {
    @GetMapping({"/", "/categories"})
    public String showAdminPage() {
        return "Admin/Category";
    }
    @GetMapping("/destinations")
    public String destinationPage() {
        return "Admin/Destination";
    }
    @GetMapping("/users")
    public String userPage() {
        return "Admin/UserManagement";
    }
}
