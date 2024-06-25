package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.DistrictsOrWards;
import com.team12.JavaTourPromotion.service.CityService;
import com.team12.JavaTourPromotion.service.DistrictsOrWardsService;
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
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RestController
public class DistrictsOrWardsController {

    @Autowired
    private DistrictsOrWardsService districtsOrWardsService;

    @GetMapping("/districts")
    public String showDistrictList(Model model) {
        model.addAttribute("districts", districtsOrWardsService.getAllDistrictsOrWards());
        return "/districts/district-list";
    }
}
