package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name" , nullable = false)
    @NotBlank(message = "Category's name must not be blank!")
    @Size(min = 5 , max = 20, message = "Category's name must be 10 to 50 characters!")
    private String Name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Categories_Destinations",
                joinColumns = @JoinColumn(name = "CategoryID"),
                inverseJoinColumns = @JoinColumn(name = "DestinationID"))
    private Set<Destinations> destinations;
}
