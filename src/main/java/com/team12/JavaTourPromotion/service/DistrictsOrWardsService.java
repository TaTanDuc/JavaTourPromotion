package com.team12.JavaTourPromotion.service;


import com.team12.JavaTourPromotion.model.DistrictsOrWards;
import com.team12.JavaTourPromotion.repository.DistrictsOrWardsRepository;
import com.team12.JavaTourPromotion.GetVM.DoWGetVM;

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

    public List<DoWGetVM> getAllDoWsByCity(Long cityId) {
        return DistrictsOrWardsRepository.findAll()
                .stream()
                .filter(dow ->
                        (dow.getCity().getId().equals(cityId)))
                .map(DoWGetVM::from)
                .toList();
    }

    public DistrictsOrWards addDistrictsOrWards(DistrictsOrWards dow) {
        return DistrictsOrWardsRepository.save(dow);
    }

    public void deleteDistrictsOrWards(Long id) {
        if (!DistrictsOrWardsRepository.existsById(id)) {
            throw new IllegalStateException("DistrictOrWards with ID " + id + " does not exist.");
        }
         DistrictsOrWardsRepository.deleteById(id);
    }
}
