package com.meli.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.test.models.dto.StatsOutDTO;
import com.meli.test.models.dto.ValidationMutantInDTO;
import com.meli.test.service.impl.MutantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.meli.test.models.messages.ErrorMessages.MESSAGE_SQUARE_MATRIX_VALIDATION;
import static com.meli.test.models.messages.ErrorMessages.MESSAGE_VALID_CHARACTER_VALIDATION;
import static org.hamcrest.CoreMatchers.any;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MutantController.class)
public class ValidateRequestBodyControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MutantService mutantService;

    @Test
    void whenInputIsEmpty_thenReturnStatus400() throws Exception {

        String[] dna = {};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        String body = objectMapper.writeValueAsString(dnaIn);

        mvc.perform(MockMvcRequestBuilders.post("/mutant")
                .contentType("application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenInputMatrixNotIsSquare_thenReturnStatus400() throws Exception {

        String[] dna = {"ATGCGA","ATGCGA","ATGCGA","ATGCGA"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        String body = objectMapper.writeValueAsString(dnaIn);

        mvc.perform(MockMvcRequestBuilders.post("/mutant")
                .contentType("application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dna").value(MESSAGE_SQUARE_MATRIX_VALIDATION));
    }

    @Test
    void whenInputContainsInvalidCharacters_thenReturnStatus400() throws Exception {

        String[] dna = {"ATGC","APGC","ATGC","ATGC"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        String body = objectMapper.writeValueAsString(dnaIn);

        mvc.perform(MockMvcRequestBuilders.post("/mutant")
                .contentType("application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dna").value(MESSAGE_VALID_CHARACTER_VALIDATION));
    }

    @Test
    void whenInputIsMutant_thenReturnStatus200() throws Exception {

        String[] dna = {"AAAA","AAAA","AAAA","AAAA"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        Mockito.when(mutantService.isMutant(Mockito.any(String[].class))).thenReturn(true);
        String body = objectMapper.writeValueAsString(dnaIn);

        mvc.perform(MockMvcRequestBuilders.post("/mutant")
                .contentType("application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void whenInputIsHuman_thenReturnStatus403() throws Exception {

        String[] dna = {"AAGG","GGTT","CCAA","AATT"};
        ValidationMutantInDTO dnaIn = new ValidationMutantInDTO(dna);
        Mockito.when(mutantService.isMutant(Mockito.any(String[].class))).thenReturn(false);
        String body = objectMapper.writeValueAsString(dnaIn);

        mvc.perform(MockMvcRequestBuilders.post("/mutant")
                .contentType("application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void whenFindGetStats_thenReturnStatusOk() throws Exception {

        String body = "";
        StatsOutDTO out = new StatsOutDTO();
        out.setCountMutantDna(1);
        out.setCountHumanDna(1);
        out.setRatio(1.0);
        Mockito.when(mutantService.getStats()).thenReturn(out);
        mvc.perform(MockMvcRequestBuilders.get("/mutant/stats")
                .contentType("application/json")
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
