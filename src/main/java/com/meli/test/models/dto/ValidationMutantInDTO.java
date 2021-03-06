package com.meli.test.models.dto;

import com.meli.test.validator.MinSizeConstraint;
import com.meli.test.validator.SquareMatrixConstraint;
import com.meli.test.validator.ValidCharacterConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationMutantInDTO {

    @NotEmpty(message = "no debe estar vacío")
    @MinSizeConstraint
    @SquareMatrixConstraint
    @ValidCharacterConstraint
    @ApiModelProperty(notes = "Secuencia de ADN")
    private String[] dna;
}
