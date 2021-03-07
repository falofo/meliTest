package com.meli.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidCharacterConstraintValidator implements ConstraintValidator<ValidCharacterConstraint, String[]> {
    @Override
    public boolean isValid(String[] values, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^[ATCG]+$");
        for (String value : values) {
            Matcher matcher = pattern.matcher(value);
            if(!matcher.matches()){
                return false;
            }
        }
        return true;
    }
}
