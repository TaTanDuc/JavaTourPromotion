package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.DestinationImages;
import com.team12.JavaTourPromotion.service.ProviceService;
import com.team12.JavaTourPromotion.service.CityService;
import com.team12.JavaTourPromotion.service.DistrictsOrWardsService;
import com.team12.JavaTourPromotion.service.CategoryService;
import com.team12.JavaTourPromotion.service.DestinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class DestinationController {

    @Autowired
    private DestinationService destinationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DistrictsOrWardsService districtsOrWardsService;
    @Autowired
    private CityService cityService; // Đảm bảo bạn đã inject CategoryService
    @Autowired
    private ProviceService proviceService;
    @GetMapping
    public String showDestinationList(Model model) {
        model.addAttribute("destination", destinationService.getAllDestination());
        return "/destinations/destination-list";
    }
    // For adding a new product
    @GetMapping("/destinations/add")
    public String showAddForm(Model model) {
        model.addAttribute("destination", new Destinations());
        model.addAttribute("categories", categoryService.getAlCatologies());
        model.addAttribute("categories", proviceService.getAllProvinces());
        model.addAttribute("categories", cityService.getAllCities());
        model.addAttribute("categories", districtsOrWardsService.getAllDistrictsOrWards());//Load categories
        return "/destinations/add-destination";
    }
    @PostMapping("/add")
    public String addDestination(@Valid Destinations destination, BindingResult result,
                             @RequestParam("image") MultipartFile imageFile
                            @RequestParam(images) ) {
        if (result.hasErrors()) {
            return "/products/add-product";
        }

        // Upload image file
        if (!imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                product.setImageUrl("/images/" + fileName); // Save the image URL
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.addProduct(product);
        return "redirect:/products";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories()); //Load categories
        return "/products/update-product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Product product,
                                BindingResult result,
                                @RequestParam("image") MultipartFile imageFile) {
        if (result.hasErrors()) {
            product.setId(id);
            return "/products/update-product";
        }

        // Upload image file
        if (!imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                product.setImageUrl("/images/" + fileName); // Save the image URL
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.updateProduct(product);
        return "redirect:/products";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

}
