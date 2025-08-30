package com.example.Week1Introduction.Week_1_.Introduction.Annotations;
import java.util.List;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotEmpty;
import org.modelmapper.internal.bytebuddy.asm.Advice;

public class EmployeeRoleValidation implements ConstraintValidator<EmployeeRoleValidationAnnotation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> role = List.of("USER","ADMIN");
        return role.contains(s);
    }
}
