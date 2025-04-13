package com.example.validator;

import com.example.annotation.IsEmailCustom;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;

public class EmailValidatorCustom implements ConstraintValidator<IsEmailCustom, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.trim().isEmpty()){
            return false;
        }
        return s.toLowerCase().startsWith("@");
    }

    @Override
    public void initialize(IsEmailCustom constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
