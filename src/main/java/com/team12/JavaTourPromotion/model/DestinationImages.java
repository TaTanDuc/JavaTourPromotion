package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DesImages")
public class DestinationImages {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Destinations destination;

    @Column(name = "Path")
    private String Path;
}
