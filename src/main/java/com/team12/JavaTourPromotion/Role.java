package com.team12.JavaTourPromotion;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Role {
    ADMIN(1),
    INSPECTOR(2),
    USER(3);
    public final long value;
}