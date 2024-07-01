package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Provinces;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record ProvinceGetVM(Long id, String name){

    public static ProvinceGetVM from(@NotNull Provinces province) {

        return new ProvinceGetVM(
                province.getId(),
                province.getName()
        );
    }
}
