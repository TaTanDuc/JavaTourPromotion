package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Cities, Long> {
}
