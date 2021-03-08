package com.meli.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS;

public class MinSizeConstraintValidator implements ConstraintValidator<MinSizeConstraint, String[]> {
    @Override
    public boolean isValid(String[] values, ConstraintValidatorContext context) {
        return values.length >= NUMBER_OF_CONSECUTIVE_LETTERS;
    }
}
