package com.meli.test.models.messages;

import static com.meli.test.models.Constants.VALID_DNA_CHARACTER;

public class ErrorMessages {
    public static final String MESSAGE_MIN_SIZE_VALIDATION = "La secuencia de dna debe contener al menos 4 elementos.";
    public static final String MESSAGE_SQUARE_MATRIX_VALIDATION = "La longitud de cada uno de los elementos de la secuencia debe ser igual al número de elementos.";
    public static final String MESSAGE_VALID_CHARACTER_VALIDATION = "Solo se admiten los caracteres " + VALID_DNA_CHARACTER;
}
