package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {
    Roles findRoleById(Long id);
}
