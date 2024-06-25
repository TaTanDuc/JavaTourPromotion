package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.DestinationImages;
import com.team12.JavaTourPromotion.model.Destinations;
import jakarta.validation.Path;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record DestinationPOSTVM(String name, String content, String imageUrl, List<DestinationImages> destinationImages, Set<Categories> categories, Long provinceID, Long cityID, Long DoW_ID) {
    public static DestinationPOSTVM from(@NotNull Destinations destination){
        return new DestinationPOSTVM(
                destination.getName(),
                destination.getContent(),
                destination.getImageUrl(),
                destination.getImages(),
                destination.getCategories(),
                destination.getProvince().getId(),
                destination.getCity().getId(),
                destination.getDoW().getId()
        );
    }
}
