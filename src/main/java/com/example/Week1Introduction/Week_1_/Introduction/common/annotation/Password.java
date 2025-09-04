package com.example.Week1Introduction.Week_1_.Introduction.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class Password implements ConstraintValidator<PasswordAnnotation,String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Debug password  =============================================");
        boolean upperLetter =false;
        System.out.println("Debug password  =============================================");
        boolean lowerLetter = false;
        System.out.println("Debug password  =============================================");
        boolean specialLetter = false;
        System.out.println("Debug password  =============================================");

        System.out.println("Debug password  =============================================");
        System.out.println(value+" lenth is =============== ");
        if(true)
        {
            System.out.println("Enter in if conditation");
            for (int i = 0; i < value.length(); i++) {
                char r = value.charAt(i);
                if(r==38 || r==36)
                {
                    specialLetter = true;
                }
                else if(65 <= r && r<=90)
                {
                    upperLetter = true;
                } else if (97 <= r && r<=122)
                {
                    lowerLetter = true;
                }
                if(upperLetter && lowerLetter && specialLetter)
                {
                    break;
                }
            }
            System.out.println((upperLetter && lowerLetter && specialLetter) + " =======");
            return upperLetter && lowerLetter && specialLetter;
        }else {
            System.out.println("FALSE ============");
            return false;
        }


    }
}
