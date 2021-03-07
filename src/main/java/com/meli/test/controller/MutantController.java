package com.meli.test.controller;

import com.meli.test.models.dto.StatsOutDTO;
import com.meli.test.models.dto.ValidationMutantInDTO;
import com.meli.test.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity isMutant(@Valid @RequestBody ValidationMutantInDTO validation){
        return mutantService.validateMutant(validation);
    }

    @GetMapping(path = "stats")
    public StatsOutDTO getStats(){
        return new StatsOutDTO(1,1,1.0);// TODO: servicio obtener stats
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

