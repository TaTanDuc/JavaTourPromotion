package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.Destinations;

import java.util.List;

public record CategoryGetVM(Long id, String name) {
    public static  CategoryGetVM from(Categories category){
        return new CategoryGetVM(
                category.getId(),
                category.getName()
        );
    }
}
