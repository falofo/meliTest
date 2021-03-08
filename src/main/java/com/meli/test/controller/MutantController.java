package com.meli.test.controller;

import com.meli.test.models.dto.StatsOutDTO;
import com.meli.test.models.dto.ValidationMutantInDTO;
import com.meli.test.service.IMutantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Valida si un humano es mutante basándose en su secuencia de ADN")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Validación de ADN corresponde a un mutante"),
            @ApiResponse(code = 400, message = "Error en los datos ingresados"),
            @ApiResponse(code = 403, message = "Validación de ADN corresponde a un humano")
    })
    @PostMapping
    public ResponseEntity<Void> isMutant(@Valid @RequestBody ValidationMutantInDTO validation){
        if(mutantService.isMutant(validation.getDna())){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "Retorna las estadísticas de las verificaciones de ADN realizadas", response = StatsOutDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")
    })
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

