package com.team12.JavaTourPromotion.controller.USER;

import com.team12.JavaTourPromotion.GetVM.UserGetVM;
import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.service.CommentService;
import com.team12.JavaTourPromotion.service.DestinationService;
import com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserControllerAPI {

    private final UserService userService;
    private final CommentService commentService;
    private final DestinationService destinationService;

    @GetMapping("/profile")
    public ResponseEntity<Optional<UserGetVM>> userProfile(String username){
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }



    @PostMapping("/comment/add")
    public Comments addComment(@RequestParam(value = "destination") Long id,@RequestParam(value = "username") String username,@RequestBody  Comments comment){
        //destinationService.avgScore();
        return commentService.addComment(id,username,comment);
    }
}
