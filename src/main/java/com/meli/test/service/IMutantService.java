package com.meli.test.service;

import com.meli.test.models.dto.StatsOutDTO;

public interface IMutantService {
    boolean isMutant(String[] dna);
    StatsOutDTO getStats();
}
