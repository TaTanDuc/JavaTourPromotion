package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
