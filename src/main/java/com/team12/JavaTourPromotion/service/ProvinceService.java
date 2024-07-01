package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Provinces;
import com.team12.JavaTourPromotion.repository.ProvinceRepository;
import com.team12.JavaTourPromotion.GetVM.ProvinceGetVM;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public List<ProvinceGetVM> getAllProvinces() {
        return provinceRepository.findAll()
                .stream()
                .map(ProvinceGetVM::from)
                .toList();
    }

    public Optional<ProvinceGetVM> getProvinceById(Long id) {
        return provinceRepository.findById(id).map(ProvinceGetVM::from);
    }

}
