package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
