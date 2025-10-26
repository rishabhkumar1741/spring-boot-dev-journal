package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import com.example.Week1Introduction.Week_1_.Introduction.common.Enum.GenderEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Optional;

public class Gender implements ConstraintValidator<GenderValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<GenderEnum> obj = GenderEnum.isValid(value);
        return obj.isPresent();
    }
}
