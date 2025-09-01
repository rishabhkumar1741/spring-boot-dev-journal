package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRole implements ConstraintValidator<EmployeeRoleValidation,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> roles = List.of("USER","ADMIN");
        return roles.contains(value);
    }
}
