package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {Password.class})
public @interface PasswordAnnotation {
    String message() default "Password sahi kar";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
