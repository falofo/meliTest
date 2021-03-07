package com.meli.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SquareMatrixConstraintValidator implements ConstraintValidator<SquareMatrixConstraint, String[]> {
    @Override
    public boolean isValid(String[] values, ConstraintValidatorContext context) {
        for (String value : values) {
            if (value.length() != values.length) {
                return false;
            }
        }
        return true;
    }
}
