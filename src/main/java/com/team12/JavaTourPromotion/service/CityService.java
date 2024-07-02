package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.repository.CityRepository;
import com.team12.JavaTourPromotion.GetVM.CityGetVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {

    private final CityRepository cityRepository;

    public List<CityGetVM> getAllCityByProvince(Long provinceId){
        return cityRepository.findAll()
                .stream()
                .filter(city ->
                        (city.getProvince().getId().equals(provinceId)))
                .map(CityGetVM::from)
                .toList();
    }
}
