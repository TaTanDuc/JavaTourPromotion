package com.team12.JavaTourPromotion.service;


import com.team12.JavaTourPromotion.model.DistrictsOrWards;
import com.team12.JavaTourPromotion.repository.DistrictsOrWardsRepository;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DistrictsOrWardsService {
    private final DistrictsOrWardsRepository DistrictsOrWardsRepository;

    public List<DistrictsOrWards> getAllDistrictsOrWards() {
        return DistrictsOrWardsRepository.findAll();
    }

    public Optional<DistrictsOrWards> getDistrictsOrWards(Long id) {
        return DistrictsOrWardsRepository.findById(id);
    }

    // Add a new product to the database
    public DistrictsOrWards addDistrictsOrWards(DistrictsOrWards city) {
        return DistrictsOrWardsRepository.save(city);
    }

    public void deleteDistrictsOrWards(Long id) {
        if (!DistrictsOrWardsRepository.existsById(id)) {
            throw new IllegalStateException("DistrictOrWards with ID " + id + " does not exist.");
        }
         DistrictsOrWardsRepository.deleteById(id);
    }
}
