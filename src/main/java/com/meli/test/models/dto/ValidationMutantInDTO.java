package com.meli.test.models.dto;

import com.meli.test.validator.MinSizeConstraint;
import com.meli.test.validator.SquareMatrixConstraint;
import com.meli.test.validator.ValidCharacterConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ValidationMutantInDTO {

    @NotEmpty(message = "no debe estar vacío")
    @MinSizeConstraint
    @SquareMatrixConstraint
    @ValidCharacterConstraint
    private String[] dna;
}
