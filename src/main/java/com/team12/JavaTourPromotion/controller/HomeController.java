package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.*;
import com.team12.JavaTourPromotion.service.*;
import com.team12.JavaTourPromotion.viewmodel.CategoryGetVM;
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

    String errorPath = "";

    @GetMapping("/destinations")
    public ResponseEntity<List<DestinationGetVM>> getAllDestinations(){
        List<DestinationGetVM> list = destinationService.getAllDestination();
        if(list.isEmpty())
            throw new RuntimeException("List of destinations is empty!");
        else
            return ResponseEntity.ok(list);
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceGetVM>> getAllProvinces(){
        List<ProvinceGetVM> list = provinceService.getAllProvinces();
        if(list.isEmpty())
            throw new RuntimeException("List of provinces is empty!");
        else
            return ResponseEntity.ok(list);
    }

    @GetMapping("/province/get/{id}")
    public ResponseEntity<ProvinceGetVM> getProvinceByID(@PathVariable Long id){
            return ResponseEntity.ok(provinceService.getProvinceById(id)
                    .orElseThrow(() -> new RuntimeException("Province with id: " + id + " can't be found!")));
    }

    @GetMapping("/destinations/search")
    public ResponseEntity<List<DestinationGetVM>> getListDesByID(
            @RequestParam(value = "ProvinceID", required = false) Long provinceId,
            @RequestParam(value = "CityID", required = false) Long cityId,
            @RequestParam(value = "DoWID", required = false) Long dowId,
            @RequestParam(value = "Categories", required = false) List<Long> categoriesId,
            @RequestParam(value = "string", required = false) String string
    ) {
        var list = destinationService.getListByRequirements(provinceId, cityId, dowId, categoriesId, string);
        if (list.isEmpty())
            throw new RuntimeException("Filtered list of destinations is empty!");
        else
            return ResponseEntity.ok(list);
    }

    @GetMapping("/destination/{id}")
    public ResponseEntity<DestinationGetVM> getDesByID(@PathVariable Long id) {
        return ResponseEntity.ok(destinationService.getDestinationById(id)
                .orElseThrow(() -> new RuntimeException("Province with id: " + id + " can't be found!")));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryGetVM>> getAllCategories(){
        List<CategoryGetVM> list = categoryService.getAllCategories();
        if(list.isEmpty())
            throw new RuntimeException("List of categories is empty!");
        else
            return ResponseEntity.ok(list);
    }

}
