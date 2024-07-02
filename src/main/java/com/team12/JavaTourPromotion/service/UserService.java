package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.GetVM.UserGetVM;
import com.team12.JavaTourPromotion.Role;
import com.team12.JavaTourPromotion.model.Bookmarks;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.repository.IRoleRepository;
import com.team12.JavaTourPromotion.repository.IUserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public List<UserGetVM> getAllUsers() {
        return userRepository.findAll()
                .stream().map(UserGetVM::from).toList();
    }

    public void addUser(@NotNull Users user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    public void setDefaultRole(String username) {
        userRepository.findByUsername(username).ifPresentOrElse(
                user -> {

                    user.getRoles().add(roleRepository.findRoleById(Role.USER.value));
                    userRepository.save(user);
                },
                () -> {
                    throw new UsernameNotFoundException("User not found");
                }
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }

    public boolean existsByUsername(String username)
    {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String username)
    {
        return userRepository.existsByEmail(username);
    }

    @Transactional
    public void banUser(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setBanned(true);
        userRepository.save(user);
    }

    @Transactional
    public void unbanUser(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setBanned(false);
        userRepository.save(user);
    }

    public Optional<UserGetVM> findUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUsername(username).map(UserGetVM::from);
    }
//
//    public void userAddBookmark(String username, Bookmarks bookmarks){
//        Users user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));;
//        ;
//    }
}
