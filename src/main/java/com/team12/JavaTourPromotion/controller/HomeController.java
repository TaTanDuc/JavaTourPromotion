package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.*;
import com.team12.JavaTourPromotion.service.*;
import com.team12.JavaTourPromotion.viewmodel.DestinationGetVM;
import com.team12.JavaTourPromotion.viewmodel.ProvinceGetVM;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    private final DestinationService destinationService;
    private final CategoryService categoryService;
    private final ProvinceService provinceService;
    private final CityService cityService;
    private final DistrictsOrWardsService districtsOrWardsService;

    String errorPath = "";

    @GetMapping("/destinations")
    public ResponseEntity<List<DestinationGetVM>> getAllDestinations(){
        return ResponseEntity.ok(destinationService.getAllDestination()
                .stream()
                .toList()
        );
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceGetVM>> getAllProvinces(){
        return ResponseEntity.ok(provinceService.getAllProvinces()
                .stream()
                .toList()
        );
    }

    @GetMapping("/province/get/{id}")
    public ResponseEntity<ProvinceGetVM> getProvinceByID(@PathVariable Long id){
            ProvinceGetVM province = provinceService.getProvinceById(id)
                    .orElseThrow(() -> new RuntimeException("Province with id: " + id + " can't be found!"));
            return ResponseEntity.ok(province);
    }

    @GetMapping("/destinations/search")
    public ResponseEntity<List<DestinationGetVM>> getListDesByID(
            @RequestParam(value = "ProvinceID", required = false) Long provinceId,
            @RequestParam(value = "CityID", required = false) Long cityId,
            @RequestParam(value = "DoWID", required = false) Long dowId,
            @RequestParam(value = "Categories", required = false) List<Long> categoriesId,
            @RequestParam(value = "string", required = false) String string
    ){
        return ResponseEntity.ok(destinationService.getListByRequirements(provinceId, cityId, dowId, categoriesId, string)
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
