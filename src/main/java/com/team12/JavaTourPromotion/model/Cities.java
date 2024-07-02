package com.team12.JavaTourPromotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cities")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50, nullable = false)
    @NotBlank(message = "City's name must not be blank!")
    @Size(min = 10, max = 50, message = "City's name must be 10 to 50 characters!")
    private String Name;

    @ManyToOne
    @JoinColumn(name = "ProvinceID", referencedColumnName = "id")
    private Provinces province;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<DistrictsOrWards> DoW;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Destinations> destinations;
}