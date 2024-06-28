package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.Cities;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.DistrictsOrWards;
import com.team12.JavaTourPromotion.model.Provinces;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record ProvinceGetVM(Long id, String name, Set<cities> citySet, Set<destinations> destinationSet){

    public static ProvinceGetVM from(@NotNull Provinces province) {
        Set<cities> simpleCities = province.getCities().stream()
                .map(city -> new cities(city.getId(), city.getName()))
                .collect(Collectors.toSet());

        Set<destinations> simpleDes = province.getDestinations().stream()
                .map(des -> new destinations(des.getId(), des.getName()))
                .collect(Collectors.toSet());

        return new ProvinceGetVM(
                province.getId(),
                province.getName(),
                simpleCities,
                simpleDes
        );
    }

    // Define a simplified City record or class
    public record cities(Long id, String name) {}
    public record destinations(Long id, String name) {}
}
