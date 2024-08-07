package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DestinationID")
    private Destinations destination;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    @Column(name = "Content", nullable = false)
    @NotBlank(message = "Comment must not be blank!")
    @Size(min = 10, max = 50, message = "Comment must be 3 or more characters!")
    private String Content;

    @Column(name = "Rating" , nullable = false)

    @Min(value = 1 , message = "Can't rate less than 1 stars!")
    @Max(value = 5 , message = "Can't rate more than 5 stars!")
    private int Rating;

    @Column(name = "ReviewedStatus", columnDefinition = "boolean default false")
    private boolean Status;

    public void setStatus(boolean status){
        this.Status = status;
    }
}
