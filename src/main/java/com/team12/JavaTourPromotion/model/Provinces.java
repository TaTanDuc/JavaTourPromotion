package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Provinces")
public class Provinces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50, nullable = false)
    @NotBlank(message = "Province's name must not be blank!")
    @Size(min = 10, max = 50, message = "Name must be 10 to 50 characters!")
    private String Name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<Cities> cities;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<Destinations> destinations;
}
