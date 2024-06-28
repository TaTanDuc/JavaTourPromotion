package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RestController
public class  CommentController {
    @Autowired
    private CommentService commentService; // Đảm bảo bạn đã inject CategoryService
    // Display a list of all products
    @GetMapping("/comments")
    public String showCommentList(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "/comments/comment-list";
    }
    // Process the form for adding a new product
    @PostMapping("/comments/add")
    public String addComment(@Valid Comments comment, BindingResult result) {
        if (result.hasErrors()) {
            return "/comments/add-comment";
        }

        commentService.addComment(comment);
        return "redirect:/comments";
    }
    @GetMapping("comments/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/comments";
    }
}
