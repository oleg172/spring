package com.spring.blog.validator.impl;


import com.spring.blog.validator.PasswordValidator;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidatorImp implements
        ConstraintValidator<PasswordValidator, String> {

    private Integer minPasswordLength = 8;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        if (password.length() < minPasswordLength) {
            return false;
        }
        return checkPassword(password);
    }

    private static boolean checkPassword(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }
        return false;
    }
}
