package com.spring.blog.validator.impl;


import com.spring.blog.model.AuthProvider;
import com.spring.blog.model.User;
import com.spring.blog.validator.PasswordValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class PasswordValidatorImp implements ConstraintValidator<PasswordValidator, User> {

    private Integer minPasswordLength = 8;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {

        if (user.getProvider() != AuthProvider.local){
            return true;
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return false;
        }
        if (user.getPassword().length() < minPasswordLength) {
            return false;
        }
        return checkPassword(user.getPassword());
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
