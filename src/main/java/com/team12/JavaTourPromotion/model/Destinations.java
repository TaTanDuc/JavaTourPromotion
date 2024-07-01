package com.team12.JavaTourPromotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "Score" , columnDefinition = "float default 0")
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

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<DestinationImages> images;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private Set<Bookmarks> bookmarks;

    @ManyToOne
    private Provinces province;

    @ManyToOne
    private Cities city;

    @ManyToOne
    private DistrictsOrWards DoW;

    @ManyToMany(mappedBy = "destinations", cascade = CascadeType.ALL)
    private Set<Categories> categories;

    public void calculateAverageScore() {
        if (comments == null || comments.isEmpty()) {
            this.Score = 0;
        } else {
            double average = comments.stream()
                    .filter(comment -> comment.getDestination().equals(this))
                    .mapToInt(Comments::getRating)
                    .average()
                    .orElse(0.0);
            this.Score = (float) average;
        }
    }
}
