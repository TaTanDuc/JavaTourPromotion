package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.DestinationImages;
import com.team12.JavaTourPromotion.model.Destinations;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record DestinationGETVM(Long id , String name, String content, String imageUrl, List<DestinationImages> destinationImages, Set<Categories> categories, Long provinceID, Long cityID, Long DoW_ID) {
    public static DestinationGETVM from(@NotNull Destinations destination){
        return DestinationGETVM.builder()
                .id(destination.getId())
                .name(destination.getName())
                .imageUrl(destination.getImageUrl())
                .destinationImages(destination.getImages())
                .categories(destination.getCategories())
                .provinceID(destination.getProvince().getId())
                .cityID(destination.getCity().getId())
                .DoW_ID(destination.getDoW().getId())
                .build();
    }
}
