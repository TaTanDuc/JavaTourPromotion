package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.service.DistrictsOrWardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
