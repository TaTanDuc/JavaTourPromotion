package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.viewmodel.DestinationGetVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destinations, Long>{
}
