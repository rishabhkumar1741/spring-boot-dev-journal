package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import com.example.Week1Introduction.Week_1_.Introduction.common.Enum.BloodGroupEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class BloodGroup implements ConstraintValidator<BloodGroupValidation,String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null || value.trim().isEmpty())
        {
            return  false;
        }
        try{
            Optional<BloodGroupEnum> bloodGroup = BloodGroupEnum.isValid(value);
            return bloodGroup.isPresent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
