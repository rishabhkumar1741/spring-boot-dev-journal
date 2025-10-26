package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {Gender.class})
public @interface GenderValidation {
    String message() default "Invalid Gender group. Allowed values are: MALE, FEMALE, OTHER" ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
