package com.meli.test.service;

import com.meli.test.models.dto.StatsOutDTO;
import com.meli.test.models.dto.ValidationMutantInDTO;
import com.meli.test.repositories.MutantRepository;
import com.meli.test.service.impl.MutantService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MutantServiceTests {

    @Mock
    MutantRepository mutantRepository;
    @InjectMocks
    MutantService mutantService;

    @Test
    public void whenMutantDna_thenResponseShouldBeTrue() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        boolean isMutantResponse = mutantService.isMutant(dnaIn.getDna());
        assertThat(isMutantResponse)
                .isEqualTo(true);
    }

    @Test
    public void whenHumanDna_thenResponseShouldBeFalse() {
        String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        boolean isMutantResponse = mutantService.isMutant(dnaIn.getDna());
        assertThat(isMutantResponse)
                .isEqualTo(false);
    }

    @Test
    public void whenMutantDna_HorizontalMatch_thenResponseShouldBeTrue() {
        String[] dna = {"AAAA","CCCC","ATGG","CCGG"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        boolean isMutantResponse = mutantService.isMutant(dnaIn.getDna());
        assertThat(isMutantResponse)
                .isEqualTo(true);
    }

    @Test
    public void whenMutantDna_VerticalMatch_thenResponseShouldBeTrue() {
        String[] dna = {"ACAA","ACCC","ACGG","ACGG"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        boolean isMutantResponse = mutantService.isMutant(dnaIn.getDna());
        assertThat(isMutantResponse)
                .isEqualTo(true);
    }

    @Test
    public void whenMutantDna_RightLeftDiagonalMatch_thenResponseShouldBeTrue() {
        String[] dna = {"CTGGT","CTGAC","CGACG","GAAGT","ATTCG"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        boolean isMutantResponse = mutantService.isMutant(dnaIn.getDna());
        assertThat(isMutantResponse)
                .isEqualTo(true);
    }

    @Test
    public void whenMutantDna_LeftRightDiagonalMatch_thenResponseShouldBeTrue() {
        String[] dna = {"CATTG","ACTAT","TACGT","GGACG","TAGAT"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        boolean isMutantResponse = mutantService.isMutant(dnaIn.getDna());
        assertThat(isMutantResponse)
                .isEqualTo(true);
    }

    @Test
    public void whenGetStats_thenResponseShouldBeOk() {
        StatsOutDTO outEx = new StatsOutDTO();
        outEx.setCountMutantDna(1);
        outEx.setCountHumanDna(2);
        outEx.setRatio(0.5);
        Mockito.when(mutantRepository.count()).thenReturn(2L);
        Mockito.when(mutantRepository.countByMutant(true)).thenReturn(1L);
        StatsOutDTO out = mutantService.getStats();
        assertThat(out).isEqualTo(outEx);
    }

    @Test
    public void whenGetStats_NoData_thenResponseShouldBeOk() {
        StatsOutDTO outEx = new StatsOutDTO();
        outEx.setCountMutantDna(0);
        outEx.setCountHumanDna(0);
        outEx.setRatio(0.0);
        Mockito.when(mutantRepository.count()).thenReturn(0L);
        Mockito.when(mutantRepository.countByMutant(true)).thenReturn(0L);
        StatsOutDTO out = mutantService.getStats();
        assertThat(out).isEqualTo(outEx);
    }

}
