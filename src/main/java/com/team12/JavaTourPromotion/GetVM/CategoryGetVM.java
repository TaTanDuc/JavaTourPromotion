package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Categories;

public record CategoryGetVM(Long id, String name) {
    public static  CategoryGetVM from(Categories category){
        return new CategoryGetVM(
                category.getId(),
                category.getName()
        );
    }
}
