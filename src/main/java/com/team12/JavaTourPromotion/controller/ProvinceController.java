package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Provinces;
import com.team12.JavaTourPromotion.service.ProviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class ProvinceController {

    @Autowired
    private final ProviceService proviceService;
    @GetMapping("/provinces/add")
    public String showAddForm(Model model) {
        model.addAttribute("province", new Provinces());
        return "/provinces/add-province";
    }
    @PostMapping("/provinces/add")
    public String addProvince(@Valid Provinces province, BindingResult result) {
        if (result.hasErrors()) {
            return "/provinces/add-province";
        }
        proviceService.addProvince(province);
        return "redirect:/provinces";
    }
    // Hiển thị danh sách danh mục
    @GetMapping("/provinces")
    public String listProvinces(Model model) {
        List<Provinces> provinces = proviceService.getAllProvinces();
        model.addAttribute("provinces", provinces);
        return "/provinces/provinces-list";
    }

    // GET request to show category edit form
    @GetMapping("/provinces/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Provinces province = proviceService.getProvinceById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid province Id:" + id));
        model.addAttribute("province", province);
        return "/provinces/update-province";
    }
    // POST request to update category
    @PostMapping("/provinces/update/{id}")
    public String updateProvince(@PathVariable("id") Long id, @Valid Provinces province,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            province.setId(id);
            return "/provinces/update-provinces";
        }
        proviceService.updateProvince(province);
        model.addAttribute("provinces", proviceService.getAllProvinces());
        return "redirect:/provinces";
    }
    // GET request for deleting category


}