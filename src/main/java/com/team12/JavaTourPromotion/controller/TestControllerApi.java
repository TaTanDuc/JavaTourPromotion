package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.Role;
import com.team12.JavaTourPromotion.model.Roles;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.repository.IRoleRepository;
import com.team12.JavaTourPromotion.repository.IUserRepository;
import com.team12.JavaTourPromotion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.team12.JavaTourPromotion.DTO.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@RestController
@RequestMapping("api/v1/test")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TestControllerApi {
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserService userService;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<String> authenticateUser(@RequestBody loginDTO data){
        Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                data.getUsername(), data.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody registerDTO data) throws IOException {

        // add check for username exists in a DB
        if(userService.existsByUsername(data.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userService.existsByEmail(data.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        Users user = new Users();
        user.setName(data.getName());
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setProfileImgPath("./static/Images/UserProfileImg/anonymous.png");
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.getRoles().add(roleRepository.findRoleById(Role.USER.value));

        userService.save(user);

        Authentication request = new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword() );
        Authentication result = daoAuthenticationProvider.authenticate( request );
        SecurityContextHolder.getContext().setAuthentication( result );

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

}
