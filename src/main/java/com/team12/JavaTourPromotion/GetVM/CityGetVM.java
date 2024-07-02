package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Cities;

public record CityGetVM(Long id, String name) {
    public static  CityGetVM from(Cities city){
        return new CityGetVM(
                city.getId(),
                city.getName()
        );
    }
}
