package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Time;
import java.util.*;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50) 
    @Size(min = 10, max = 50, message = "Name must be 10 to 50 characters")
    private String name;

    @Column(name = "Username", length = 20, nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    @Size(min = 8, max = 20, message = "Username must be 8 to 20 characters!")
    private String username;

    @Column(name = "Password", nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "Email", length = 50)
    @Email
    @Size(min = 10, max = 50, message = "Email must be between 10 and 50 characters!")
    private String email;

    @Column(name = "ProfileImagePath")
    private String profileImgPath;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bookmarks> bookmarks;

    @Column(name = "Banned", columnDefinition = "boolean default false")
    private boolean banned;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<Roles> userRoles = this.getRoles();
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean Banned) {
        this.banned = Banned;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Users user = (Users) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}
