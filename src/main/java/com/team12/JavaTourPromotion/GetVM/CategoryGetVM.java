package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Categories;
import jakarta.validation.constraints.NotNull;

public record CategoryGetVM(Long id, String name) {
    public static  CategoryGetVM from(@NotNull Categories category){
        return new CategoryGetVM(
                category.getId(),
                category.getName()
        );
    }
}
