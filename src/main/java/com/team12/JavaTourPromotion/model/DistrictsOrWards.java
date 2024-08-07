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
@Table(name = "DoW")
public class DistrictsOrWards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50, nullable = false)
    @NotBlank(message = "District or ward's name must not be blank!")
    @Size(min = 10, max = 50, message = "Name must be 10 to 50 characters!")
    private String Name;

    @ManyToOne
    @JoinColumn(name = "CityID", referencedColumnName = "id")
    private Cities city;

    @OneToMany(mappedBy = "DoW", cascade = CascadeType.ALL)
    private Set<Destinations> destinations;

}
