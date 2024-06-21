package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Provinces;
import com.team12.JavaTourPromotion.repository.ProvinceRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProviceService {
    private final ProvinceRepository provinceRepository;
    public List<Provinces> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Optional<Provinces> getProvinceById(Long id) {
        return provinceRepository.findById(id);
    }
    // Add a new product to the database
    public Provinces addProvince(Provinces provinces) {
        return provinceRepository.save(provinces);
    }
    // Update an existing product
    public Provinces updateProvince(@NotNull Provinces province) {
        Provinces existingProvince = provinceRepository.findById(province.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " + province.getId() + " does not exist."));
        existingProvince.setName(province.getName());

        return provinceRepository.save(existingProvince);
    }
    // Delete a product by its id
    public void deleteProvinceById(Long id) {
        if (!provinceRepository.existsById(id)) {
            throw new IllegalStateException("Province with ID " + id + " does not exist.");
        }
        provinceRepository.deleteById(id);
    }

}
