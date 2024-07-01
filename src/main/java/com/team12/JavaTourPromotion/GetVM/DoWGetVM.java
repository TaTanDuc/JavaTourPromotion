package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.DistrictsOrWards;

public record DoWGetVM(Long id, String name) {
    public static  DoWGetVM from(DistrictsOrWards dow){
        return new DoWGetVM(
                dow.getId(),
                dow.getName()
        );
    }
}

