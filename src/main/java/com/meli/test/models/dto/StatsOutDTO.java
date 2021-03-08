package com.meli.test.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "countMutantDna", "countHumanDna", "ratio" })
public class StatsOutDTO {
    @ApiModelProperty(notes = "Número de mutantes identificados")
    @JsonProperty("count_mutant_dna")
    private Integer countMutantDna;
    @ApiModelProperty(notes = "Número de pesonas validadas")
    @JsonProperty("count_human_dna")
    private Integer countHumanDna;
    @ApiModelProperty(notes = "Proporción de mutantes identificados")
    private Double ratio;
}
