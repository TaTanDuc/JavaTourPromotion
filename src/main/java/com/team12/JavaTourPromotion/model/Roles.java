package com.team12.JavaTourPromotion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Roles")
public class Roles implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", length = 50, nullable = false)
    @Size(max = 50, message = "Name must be less then 50 characters!")
    private String Name;

    @Size(max = 250, message = "Description must be less than 250 characters")
    @Column(name = "Description", length = 250)
    private String Description;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Users> users = new HashSet<>();

    @Override
    public String getAuthority(){
        return Name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return
                false;
        Roles role = (Roles) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}