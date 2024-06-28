package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.*;
import com.team12.JavaTourPromotion.service.*;
import com.team12.JavaTourPromotion.viewmodel.ProvinceGetVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    private final DestinationService destinationService;
    private final CategoryService categoryService;
    private final ProvinceService provinceService;
    private final CityService cityService;
    private final DistrictsOrWardsService districtsOrWardsService;

    @GetMapping("/destinations")
    public List<Destinations> getAllDestinations(){
        return destinationService.getAllDestination();
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceGetVM>> getAllProvinces(){
        return ResponseEntity.ok(provinceService.getAllProvinces()
                .stream()
                .toList()
        );
    }

    @GetMapping("/cities")
    public List<Cities> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/districtOrWards")
    public List<DistrictsOrWards> getAllDoWs(){
        return districtsOrWardsService.getAllDistrictsOrWards();
    }

    @GetMapping("/categories")
    public List<Categories> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
