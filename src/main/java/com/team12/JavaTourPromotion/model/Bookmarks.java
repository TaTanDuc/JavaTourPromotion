package com.team12.JavaTourPromotion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bookmarks")
public class Bookmarks {
    @Id
    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "DestinationID")
    @JsonIgnore
    private Destinations destination;

}
