package com.team12.JavaTourPromotion.controller.ADMIN;


import com.team12.JavaTourPromotion.model.Reports;
import com.team12.JavaTourPromotion.service.ReportService;
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
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService; // Đảm bảo bạn đã inject CategoryService
    // Display a list of all products
    @GetMapping("/reports")
    public String showReportList(Model model) {
        model.addAttribute("reports", reportService.getAllReports());
        return "/reports/reports-list";
    }
    // For adding a new product
    @GetMapping("/reports/add")
    public String showAddForm(Model model) {
        model.addAttribute("reports", new Reports());
        model.addAttribute("users", userService.getAllUsers()); //Load categories
        return "/reports/add-report";
    }
    @PostMapping("/reports/add")
    public String addReport(@Valid Reports report, BindingResult result) {
        if (result.hasErrors()) {
            return "/reports/add-report";
        }

        // Upload image file

        reportService.addReport(report);
        return "redirect:/reports";
    }
    // For editing a product

    // Handle request to delete a product
    @GetMapping("/reports/delete/{id}")
    public String deleteReport

















    (@PathVariable Long id) {
        reportService.deleteReportById(id);
        return "redirect:/reports";
    }
}
