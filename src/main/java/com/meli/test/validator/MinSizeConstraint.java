package com.meli.test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSizeConstraint {
    String message() default "La secuencia de dna debe contener al menos 4 elementos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
