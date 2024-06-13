package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name", length = 50, nullable = false)
    @Size(min = 10, max = 50, message = "Name must be 10 to 50 characters")
    private String Name;

    @Column(name = "Username", length = 20, nullable = false)
    @Size(min = 8, max = 20, message = "Username must be 8 to 20 characters")
    private String Username;

    @Column(name = "Password", length = 20, nullable = false)
    @Size(min = 8, max = 20, message = "Password must be 8 to 20 characters")
    private String Password;

    @Column(name = "ProfileImagePath")
    private String ProfileImgPath;

    @Column(name = "Role")
    private String Role;
}
