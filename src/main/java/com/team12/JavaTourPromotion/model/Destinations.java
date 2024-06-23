package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Destinations")
public class Destinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50, nullable = false)
    @NotBlank(message = "Destination's name must not be blank!")
    @Size(min = 10, max = 50, message = "Name must be 10 to 50 characters!")
    private String Name;

    @Column(name = "Score")
    @Min(value = 0, message = "Score must be at least 0!")
    @Max(value = 5, message = "Score must be at most 10!")
    private float Score;

    @Column(name = "Content")
    @NotBlank(message = "Content must not be blank!")
    @Size(min = 50, message = "Content must be 50 characters or more!")
    private String Content;

    @Column(name = "ImageUrl")
    @NotBlank(message = "Image must not be blank!")
    private String ImageUrl;

    @OneToMany(mappedBy = "destination")
    private List<Comments> comments;

    @OneToMany(mappedBy = "destination")
    private List<DestinationImages> images;

    @OneToMany(mappedBy = "destination")
    private Set<Bookmarks> bookmarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProvinceID")
    private Provinces province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CityID")
    private Cities city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DoW_ID")
    private DistrictsOrWards DoW;

    @ManyToMany(mappedBy = "destinations")
    private Set<Categories> categories;
}
