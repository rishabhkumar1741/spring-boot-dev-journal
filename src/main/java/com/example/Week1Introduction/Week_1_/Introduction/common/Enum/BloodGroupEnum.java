package com.example.Week1Introduction.Week_1_.Introduction.common.Enum;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public  enum BloodGroupEnum {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String value;
    BloodGroupEnum(String value){
        this.value = value;
    }

    public String getSymbol(){
        return this.value;
    }

    public  static String validBloodGroup()
    {
        return Arrays.stream(BloodGroupEnum.values()).map(x->x.value).collect(Collectors.joining(", "));
    }

    public static Optional<BloodGroupEnum> isValid(String value)
    {
        return Arrays.stream(BloodGroupEnum.values()).filter(x->x.value.equalsIgnoreCase(value)).findFirst();
    }

}
