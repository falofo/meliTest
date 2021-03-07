package com.meli.test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = SquareMatrixConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SquareMatrixConstraint {
    String message() default "La longitud de cada uno de los elementos de la secuencia debe ser igual al numero de elementos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
