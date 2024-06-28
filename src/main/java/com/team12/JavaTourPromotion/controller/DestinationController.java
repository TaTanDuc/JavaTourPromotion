package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class DestinationController {
    @Autowired
    private final DestinationService destinationService;

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
    @GetMapping({"/", "/destinations"})
    public String showDestinationList(Model model) {
        model.addAttribute("destination", destinationService.getAllDestination());
        return "Homepage/Home";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("destinations", new Destinations());
        model.addAttribute("categories", categoryService.getAlCatologies());
        model.addAttribute("provinces", proviceService.getAllProvinces());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("dows", districtsOrWardsService.getAllDistrictsOrWards());//Load categories
        return "/destinations/add-destination";
    }
    @PostMapping("/destinations/add")
    public String addDestination(@Valid Destinations destination, BindingResult result,
                             @RequestParam("ImageUrl") MultipartFile imageFile,
                             @RequestParam("images") List<MultipartFile> imageFiles) {
        if (result.hasErrors()) {
            return "/destinations/add-destination";
        }

        // Upload image file
        if (!imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                destination.setImageUrl("/images/" + fileName); // Save the image URL
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<DestinationImages> destinationImages = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    Path path = Paths.get("src/main/resources/static/images/" + fileName);
                    Files.write(path, bytes);

                    DestinationImages destinationImage = new DestinationImages();
                    destinationImage.setPath("/images/" + fileName);
                    destinationImage.setDestination(destination);
                    destinationImages.add(destinationImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        destination.setImages(destinationImages);

        destinationService.addDestination(destination);
        return "redirect:/destinations";
    }
    // For editing a product
    @GetMapping("/destinations/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Destinations destination = destinationService.getDestinationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid destination Id:" + id));
        model.addAttribute("destinations", destination);
        model.addAttribute("categories", categoryService.getAlCatologies());
        model.addAttribute("provinces", proviceService.getAllProvinces());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("dows", districtsOrWardsService.getAllDistrictsOrWards());
        return "/destinations/update-destination";
    }
    @PostMapping("/destinations/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Destinations destination,
                                BindingResult result,
                                @RequestParam("ImageUrl") MultipartFile imageFile,
                                @RequestParam("images") List<MultipartFile> imageFiles) {
        if (result.hasErrors()) {
            destination.setId(id);
            return "/destinations/update-destination";
        }
        Destinations existingDestination = destinationService.getDestinationById(destination.getId())
                .orElseThrow(() -> new IllegalStateException("Destination with ID " +
                        destination.getId() + " does not exist."));
        // Upload image file
        if (!imageFile.isEmpty()) {
            existingDestination.setImageUrl(destination.getImageUrl());
        }
        else{
            try {
                byte[] bytes = imageFile.getBytes();
                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                existingDestination.setImageUrl("/images/" + fileName); // Save the image URL
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<DestinationImages> destinationImages = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    Path path = Paths.get("src/main/resources/static/images/" + fileName);
                    Files.write(path, bytes);

                    DestinationImages destinationImage = new DestinationImages();
                    destinationImage.setPath("/images/" + fileName);
                    destinationImage.setDestination(destination);
                    destinationImages.add(destinationImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                existingDestination.setImages(destinationImages);
            }
        }
        existingDestination.setImages(destinationImages);

        destinationService.updateDestination(destination);
        return "redirect:/destinations";
    }
    // Handle request to delete a product
    @GetMapping("/destinations/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        destinationService.deleteDestinationById(id);
        return "redirect:/destinations";
    }

}
