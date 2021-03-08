package com.meli.test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.meli.test.models.messages.ErrorMessages.MESSAGE_VALID_CHARACTER_VALIDATION;

@Constraint(validatedBy = ValidCharacterConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCharacterConstraint {
    String message() default MESSAGE_VALID_CHARACTER_VALIDATION;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
