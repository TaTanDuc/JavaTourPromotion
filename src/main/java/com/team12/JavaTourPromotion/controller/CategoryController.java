package com.team12.JavaTourPromotion.controller;

import org.springframework.http.ResponseEntity;
import com.team12.JavaTourPromotion.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

//    @GetMapping("/categories")
//    public ResponseEntity<List<CategoryGETVM>> getAllCategories(Integer pageNo, Integer pageSize, String sortBy){
//        return  ResponseEntity.ok(categoryService.getAllCategories(
//                        pageNo == null ? 0 : pageNo,
//                        pageSize == null ? 20 : pageSize,
//                        sortBy == null ? "id" : sortBy)
//                .stream()
//                .map(CategoryGETVM::from)
//                .toList()
//        );
//    }
//
//    @DeleteMapping("/category/{id}")
//    public ResponseEntity<Void> deleteCategoryByID(@RequestParam Long id){
//        categoryService.deleteCategoryById(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("category/get/{id}")
//    public ResponseEntity<CategoryGETVM> getCategoryByID(@RequestParam Long id){
//        return ResponseEntity.ok(categoryService.getCategoryById(id)
//                .map(CategoryGETVM::from)
//                .orElse(null));
//    }
//
//    @GetMapping("category/search")
//    public ResponseEntity<List<CategoryGETVM>> searchCategory(String keyword){
//        return ResponseEntity.ok(categoryService.searchCategory(keyword)
//                .stream()
//                .map(CategoryGETVM::from)
//                .toList()
//        );
//    }
}
