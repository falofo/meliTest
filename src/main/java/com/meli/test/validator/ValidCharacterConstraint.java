package com.meli.test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ValidCharacterConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCharacterConstraint {
    String message() default "Solo se admiten los caracteres ATCG.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
