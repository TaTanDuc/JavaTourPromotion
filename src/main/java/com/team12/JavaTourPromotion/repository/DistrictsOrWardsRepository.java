package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.DistrictsOrWards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictsOrWardsRepository extends JpaRepository<DistrictsOrWards, Long>{
}
