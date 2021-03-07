package com.meli.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinSizeConstraintValidator implements ConstraintValidator<MinSizeConstraint, String[]> {
    @Override
    public boolean isValid(String[] values, ConstraintValidatorContext context) {
        return values.length > 4;
    }
}
