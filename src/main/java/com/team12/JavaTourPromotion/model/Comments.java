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
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "UserID")
    @JoinColumn(name = "Users", referencedColumnName = "Id")
    @ManyToOne
    private long UserID;

    @Column(name = "DestinationID", nullable = false)
    @JoinColumn(name = "Destinations", referencedColumnName = "Id")
    @ManyToOne
    private long DestinationID;

    @Column(name = "Content")
    private String Content;

    @Column(name = "Rating")
    private int Rating;

    @Column(name = "Status")
    private boolean Status;
}
