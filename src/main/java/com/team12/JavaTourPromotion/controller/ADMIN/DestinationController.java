package com.team12.JavaTourPromotion.controller.ADMIN;

import com.team12.JavaTourPromotion.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping({"/", "/destinations"})
    public String showDestinationList(Model model) {
        return "Homepage/Home";
    }
    @GetMapping("/About")
    public String showInformationAbout(Model model) {
        return "Homepage/About";
    }
    @GetMapping("/Contact")
    public String showContact(Model model) {
        return "Homepage/Contact";
    }
    @GetMapping("/Login")
    public String showLogin(Model model) {
        return "Login/login";
    }  
//    @GetMapping("/register")
//    public String showRegister(Model model) {
//        return "Login/register";
//    }
    @GetMapping("/DetailDestination/{id}")
    public String showDetailDestination(@PathVariable Long id,Model model) {
        model.addAttribute("destinationId", id);
        return "Homepage/Detail";
    } 
//     @GetMapping("/books")
// //  public ResponseEntity<List<DES>> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {
// //  return ResponseEntity.ok(destinationService.getAllDestination(
// //  pageNo == null ? 0 : pageNo,
// //  pageSize == null ? 20 : pageSize,
// //  sortBy == null ? "id" : sortBy)
// //  .stream()
// //  .map(BookGetVm::from)
// //  .toList());
// //  }
}
