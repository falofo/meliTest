package com.meli.test.controller;

import com.meli.test.models.dto.StatsOutDTO;
import com.meli.test.models.dto.ValidationMutantInDTO;
import com.meli.test.service.IMutantService;
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
    private IMutantService mutantService;

    @PostMapping
    public ResponseEntity<Void> isMutant(@Valid @RequestBody ValidationMutantInDTO validation){
        if(mutantService.isMutant(validation.getDna())){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path = "stats")
    public StatsOutDTO getStats(){
        return mutantService.getStats();
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

