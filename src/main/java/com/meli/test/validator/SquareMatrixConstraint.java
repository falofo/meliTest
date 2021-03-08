package com.meli.test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.meli.test.models.messages.ErrorMessages.MESSAGE_SQUARE_MATRIX_VALIDATION;

@Constraint(validatedBy = SquareMatrixConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SquareMatrixConstraint {
    String message() default MESSAGE_SQUARE_MATRIX_VALIDATION;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
