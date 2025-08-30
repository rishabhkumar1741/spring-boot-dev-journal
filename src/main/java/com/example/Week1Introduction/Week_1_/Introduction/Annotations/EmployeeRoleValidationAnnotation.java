package com.example.Week1Introduction.Week_1_.Introduction.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {EmployeeRoleValidation.class})
public @interface EmployeeRoleValidationAnnotation {

    String message() default "Role pass karo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};



}
