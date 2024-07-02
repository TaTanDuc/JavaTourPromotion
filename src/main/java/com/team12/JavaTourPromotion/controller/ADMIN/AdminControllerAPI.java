package com.team12.JavaTourPromotion.controller.ADMIN;

import com.team12.JavaTourPromotion.GetVM.CategoryGetVM;
import com.team12.JavaTourPromotion.GetVM.ProvinceGetVM;
import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
import com.team12.JavaTourPromotion.service.CategoryService;
import com.team12.JavaTourPromotion.service.DestinationService;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllerAPI {

    private final CategoryService categoryService;
    private final DestinationRepository destinationRepository;
    private final DestinationService destinationService;
    private final UserService userService;

    @PostMapping("/category/add")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories category){
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Categories category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found on :: "+ id));
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/destination/add")
    public ResponseEntity<Destinations> addDestination(@RequestBody Destinations destination){
        destinationService.addDestination(destination);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/destination/edit/{id}")
    public ResponseEntity<Destinations> editDestination(@PathVariable Long id){
        var des = destinationRepository.getDesById(id);
        destinationService.updateDestination(des);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/ban/{username}")
    public ResponseEntity banUser(@PathVariable String username){
        userService.banUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/unban/{username}")
    public ResponseEntity unBanUser(@PathVariable String username){
        userService.unbanUser(username);
        return ResponseEntity.ok().build();
    }
}
