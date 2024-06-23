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
    @GetMapping("/cities/add")
    public String showAddForm(Model model) {
        model.addAttribute("cities", new Cities());
        model.addAttribute("provinces", provinceService.getAllProvinces()); //Load categories
        return "/cities/add-city";
    }
    // Process the form for adding a new product
    @PostMapping("/cities/add")
    public String addCity(@Valid Cities city, BindingResult result) {
        if (result.hasErrors()) {
            return "/cities/add-city";
        }

        cityService.addCity(city);
        return "redirect:/cities";
    }
    // For editing a product
    @GetMapping("/cities/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Cities city = cityService.getCityById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cityId:" + id));
        model.addAttribute("cities", city);
        model.addAttribute("provinces", provinceService.getAllProvinces()); //Load provinces
        return "/cities/update-city";
    }
    // Process the form for updating a product
    @PostMapping("/cities/update/{id}")
    public String updateCity(@PathVariable Long id, @Valid Cities city,
                                BindingResult result) {
        if (result.hasErrors()) {
            city.setId(id); // set id to keep it in the form in case of errors

            return "/cities/update-city";
        }
        cityService.updateCity(city);
        return "redirect:/cities";
    }
}
