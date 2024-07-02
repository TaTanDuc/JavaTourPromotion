package com.team12.JavaTourPromotion.controller.ADMIN;

import com.team12.JavaTourPromotion.GetVM.CategoryGetVM;
import com.team12.JavaTourPromotion.GetVM.ProvinceGetVM;
import com.team12.JavaTourPromotion.GetVM.UserGetVM;
import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.DestinationImages;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
import com.team12.JavaTourPromotion.service.CategoryService;
import com.team12.JavaTourPromotion.service.DestinationService;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Destination;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
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
    @DeleteMapping("/destination/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        Destinations destinations = destinationService.getDestinationById11(id)
                .orElseThrow(() -> new RuntimeException("Destination not found on :: "+ id));
        destinationService.deleteDestinationById(id);
        return ResponseEntity.ok().build();
    }
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";
//    @PostMapping("/destination/add")
//    public ResponseEntity<Destinations> addDestination(@RequestBody Destinations destination,
//                                                      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
//                                                      @RequestParam(value = "imageFiles", required = false) MultipartFile[] imageFiles) {
//        try {
//            if (imageFile != null && !imageFile.isEmpty()) {
//                handleSingleImageUpload(destination, imageFile);
//            }
//
//            if (imageFiles != null && imageFiles.length > 0) {
//                handleMultipleImageUploads(destination, imageFiles);
//            }
//
//            destinationService.addDestination(destination);
//
//            return ResponseEntity.ok().body(destination);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    private void handleSingleImageUpload(Destinations destination, MultipartFile imageFile) throws IOException {
//        byte[] bytes = imageFile.getBytes();
//        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//        Path path = Paths.get(UPLOAD_DIR + fileName);
//        Files.write(path, bytes);
//
//        destination.setImageUrl("/images/" + fileName); // Save the image URL
//    }
//
//    private void handleMultipleImageUploads(Destinations destination, MultipartFile[] imageFiles) throws IOException {
//        List<DestinationImages> destinationImages = new ArrayList<>();
//        for (MultipartFile file : imageFiles) {
//            if (!file.isEmpty()) {
//                byte[] bytes = file.getBytes();
//                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//                Path path = Paths.get(UPLOAD_DIR + fileName);
//                Files.write(path, bytes);
//
//                DestinationImages destinationImage = new DestinationImages();
//                destinationImage.setPath("/images/" + fileName);
//                destinationImage.setDestination(destination);
//                destinationImages.add(destinationImage);
//            }
//        }
//        destination.setImages(destinationImages);
//    }
@PostMapping("/destination/add")
public ResponseEntity<?> addDestination(@Valid @RequestBody Destinations destination, BindingResult result,
                                        @RequestParam("ImageUrl") MultipartFile imageFile,
                                        @RequestParam("images") List<MultipartFile> imageFiles) {

    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    try {
        if (!imageFile.isEmpty()) {
            byte[] bytes = imageFile.getBytes();
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            destination.setImageUrl(File.separator + "images" + File.separator + fileName); // Save the image URL
            Path path = Paths.get("src/main/resources/static/images/" + fileName);
            Files.write(path, bytes);
        }

        List<DestinationImages> destinationImages = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, bytes);

                DestinationImages destinationImage = new DestinationImages();
                destinationImage.setPath(File.separator + "images" + File.separator + fileName);
                destinationImage.setDestination(destination);
                destinationImages.add(destinationImage);
            }
        }
        destination.setImages(destinationImages);

        destinationService.addDestination(destination);
        return ResponseEntity.ok().build();
    } catch (IOException e) {
        e.printStackTrace(); // Xử lý lỗi khi ghi file
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @PutMapping("/destination/edit/{id}")
    public ResponseEntity<Destinations> editDestination(@PathVariable Long id){
        var des = destinationRepository.getDesById(id);
        destinationService.updateDestination(des);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/ban/{username}")
//    public ResponseEntity banUser(@PathVariable String username){
//        userService.banUser(username);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/unban/{username}")
//    public ResponseEntity unBanUser(@PathVariable String username){
//        userService.unbanUser(username);
//        return ResponseEntity.ok().build();
//    }
@PutMapping("/ban/{username}")
public ResponseEntity<Void> banUser(@PathVariable String username) {
    try {
        userService.banUser(username);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        System.err.println("Error banning user: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @PutMapping("/unban/{username}")
    public ResponseEntity<Void> unBanUser(@PathVariable String username) {
        try {
            userService.unbanUser(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error unbanning user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserGetVM>> getAllUsers() {
        List<UserGetVM> list = userService.getAllUsers();
        if (list.isEmpty())
            throw new RuntimeException("List of users is empty!");
        else
            return ResponseEntity.ok(list);
    }
//@PutMapping("/ban/{username}")
//public ResponseEntity<Void> banUser(@PathVariable String username) {
//    try {
//        userService.banUser(username);
//        return ResponseEntity.ok().build();
//    } catch (Exception e) {
//        // Log the error for debugging
//        System.err.println("Error banning user: " + e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//}
//
//    @PutMapping("/unban/{username}")
//    public ResponseEntity<Void> unBanUser(@PathVariable String username) {
//        try {
//            userService.unbanUser(username);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            // Log the error for debugging
//            System.err.println("Error unbanning user: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
