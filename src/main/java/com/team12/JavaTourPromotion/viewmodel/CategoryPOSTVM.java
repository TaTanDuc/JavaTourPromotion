package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.DestinationImages;
import com.team12.JavaTourPromotion.model.Destinations;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record CategoryPOSTVM(String name, Set<Destinations> destinationsSet) {
    public static CategoryPOSTVM from(@NotNull Categories category){
        return new CategoryPOSTVM(
                category.getName(),
                category.getDestinations()
        );
    }
}
