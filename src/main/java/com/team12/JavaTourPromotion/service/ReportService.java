package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Reports;
import com.team12.JavaTourPromotion.repository.ReportRepository;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Reports> getAllReports() {
        return reportRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<Reports> getReportById(Long id) {
        return reportRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param category the category to add
     */
    public void addReport(Reports report) {
        reportRepository.save(report);
    }
    /**
     * Update an existing category.
     * @param eports the category with updated information
     */

    public void deleteReportById(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new IllegalStateException("Reports with ID " + id + " does not exist.");
        }
        reportRepository.deleteById(id);
    }
}
