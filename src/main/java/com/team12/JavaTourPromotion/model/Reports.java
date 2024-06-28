package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Content")
    @NotBlank(message = "Content must not be blank!")
    @Size(min = 10,message = "Content must be 10 or more characters!")
    private String Content;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    @OneToOne
    @JoinColumn(name = "CommentID")
    private Comments comment;
}
