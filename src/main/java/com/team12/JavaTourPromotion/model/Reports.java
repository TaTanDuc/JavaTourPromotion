package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CommentID")
    private Comments comment;
}
