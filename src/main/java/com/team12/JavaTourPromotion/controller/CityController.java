package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Cities;
import com.team12.JavaTourPromotion.service.CityService;
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
@Controller
@RequiredArgsConstructor

public class CityController {

    @Autowired
    private ProviceService provinceService;
    @Autowired
    private CityService cityService; // Đảm bảo bạn đã inject CategoryService
    // Display a list of all products
    @GetMapping("/cities")
    public String showCityList(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "/cities/city-list";
    }
    // For adding a new product

    // For editing a product

}
