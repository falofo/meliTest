package com.meli.test.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "countMutantDna", "countHumanDna", "ratio" })
public class StatsOutDTO {
    @JsonProperty("count_mutant_dna")
    private Integer countMutantDna;
    @JsonProperty("count_human_dna")
    private Integer countHumanDna;
    private Double ratio;
}
