package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Cities;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class UserService {

    private final UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    // Retrieve all products from the database
    // Retrieve a product by its id
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }
    // Add a new product to the database
    public Users addUser(Users user) {
        return userRepository.save(user);
    }
    // Update an existing product
    public Users updateUser(@NotNull Users user) {
        Users existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("User with ID " + user.getId() + " does not exist."));
        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setProfileImgPath(user.getProfileImgPath());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
    // Delete a product by its id


}
