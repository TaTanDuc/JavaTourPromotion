package com.team12.JavaTourPromotion.controller;

import org.springframework.ui.Model;
import com.team12.JavaTourPromotion.service.CategoryService;
import com.team12.JavaTourPromotion.model.Categories;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Categories());
        return "/categories/add-category";
    }

    @PostMapping("/add")
    public String addCategory(@Valid Categories category, BindingResult result) {
        if (result.hasErrors()) {
            return "/categories/add-category";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("")
    public String listCategories(Model model) {
        List<Categories> categories = categoryService.getAlCatologies();
        model.addAttribute("categories", categories);
        return "/categories/categories-list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Categories category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "/categories/update-category";
    }
    // POST request to update category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @Valid Categories
            category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "/categories/update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.getAlCatologies());
        return "redirect:/categories";
    }
    // GET request for deleting category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        Categories category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        categoryService.deleteCategoryById(id);
        model.addAttribute("categories", categoryService.getAlCatologies());
        return "redirect:/categories";
    }
}
