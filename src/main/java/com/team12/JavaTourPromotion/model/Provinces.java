package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class Provinces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name", length = 50, nullable = false)
    @Size(min = 10, max = 50, message = "Name must be 10 to 50 characters")
    private String Name;

    @OneToMany(mappedBy = "Provinces")
    private List<Provinces> Provinces;
}
