package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Reports, Long> {
}
