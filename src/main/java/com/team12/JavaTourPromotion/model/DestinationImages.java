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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Destinations destination;

    @Column(name = "Path")
    private String Path;
}
