package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Provinces, Long> {
}
