package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import com.example.Week1Introduction.Week_1_.Introduction.common.Enum.BloodGroupEnum;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {BloodGroup.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface BloodGroupValidation {
    String message() default "Invalid blood group. Allowed values are: A+, A-, B+, B-, AB+, AB-, O+, O-" ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
