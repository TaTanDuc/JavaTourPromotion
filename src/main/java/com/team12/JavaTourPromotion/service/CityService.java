package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.repository.CityRepository;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

import com.team12.JavaTourPromotion.model.Cities;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {
    private final CityRepository cityRepository;
    public List<Cities> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<Cities> getCityById(Long id) {
        return cityRepository.findById(id);
    }
    // Add a new product to the database
    public Cities addCity(Cities city) {
        return cityRepository.save(city);
    }
    // Update an existing product
    public Cities updateCity(@NotNull Cities city) {
        Cities existingCities = cityRepository.findById(city.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " + city.getId() + " does not exist."));
        existingCities.setProvince(city.getProvince());
        return cityRepository.save(existingCities);
    }
    // Delete a product by its id
    public void deleteCityById(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new IllegalStateException("City with ID " + id + " does not exist.");
        }
        cityRepository.deleteById(id);
    }
}
