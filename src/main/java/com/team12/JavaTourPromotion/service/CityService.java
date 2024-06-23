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
}
