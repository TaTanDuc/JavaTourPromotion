package com.team12.JavaTourPromotion.controller.USER;

import com.team12.JavaTourPromotion.GetVM.UserGetVM;
import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.CommentService;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserControllerAPI {

    private final UserService userService;
    private final CommentService commentService;

    @GetMapping("/profile/{username}")
    public ResponseEntity<Optional<UserGetVM>> userProfile(@PathVariable Principal username){
        return ResponseEntity.ok(userService.findUserByUsername(username.getName()));
    }

    @GetMapping("/comment/add")
    public Comments addComment(@RequestParam(value = "destination") Long id, Comments comment, Principal principal){
        return commentService.addComment(id,principal.getName(),comment);
    }
}
