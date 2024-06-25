package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.Destinations;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record CategoryGETVM(Long id , String name, Set<Destinations> destinationsSet) {
    public static CategoryGETVM from(@NotNull Categories category){
        return CategoryGETVM.builder()
                .id(category.getId())
                .name(category.getName())
                .destinationsSet(category.getDestinations())
                .build();
    }
}
