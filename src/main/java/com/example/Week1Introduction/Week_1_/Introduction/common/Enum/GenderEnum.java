package com.example.Week1Introduction.Week_1_.Introduction.common.Enum;

import java.util.Arrays;
import java.util.Optional;

public enum GenderEnum {
    MALE,
    FEMALE,
    OTHER;

    public static Optional<GenderEnum> isValid(String value)
    {
        if (value == null) return Optional.empty();
        String v = value.trim();
        if (v.isEmpty()) return Optional.empty();
        return Arrays.stream(GenderEnum.values()).filter(x->x.toString().equalsIgnoreCase(value)).findFirst();
    }
}
