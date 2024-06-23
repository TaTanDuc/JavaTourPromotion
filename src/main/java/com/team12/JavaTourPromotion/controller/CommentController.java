package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.service.CommentService;
import com.team12.JavaTourPromotion.service.DestinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor


public class  CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private DestinationService destinationService;// Đảm bảo bạn đã inject CategoryService
    // Display a list of all products
    @GetMapping("/comments")
    public String showCommentList(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "/comments/comment-list";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute Comments comment, BindingResult result, @RequestParam Long destinationId) {
        if (result.hasErrors()) {
            return "redirect:/destination/" + destinationId; // Replace with the actual view for destination details
        }

        Destinations destination = destinationService.getDestinationById(destinationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid destination Id:" + destinationId));

        comment.setDestination(destination);
        commentService.addComment(comment);

        return "redirect:/destination/" + destinationId; // Replace with the actual view for destination details
    }
    @GetMapping("comments/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/comments";
    }
}
