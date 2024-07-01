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

    public List<Reports> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Reports> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public void addReport(Reports report) {
        reportRepository.save(report);
    }

    public void deleteReportById(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new IllegalStateException("Reports with ID " + id + " does not exist.");
        }
        reportRepository.deleteById(id);
    }
}
