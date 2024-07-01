package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destinations, Long>{
    Destinations getDesById(Long id);
}
