package com.meli.test.controller;

import com.meli.test.dto.StatsOutDTO;
import com.meli.test.dto.ValidationMutantInDTO;
import com.meli.test.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity isMutant(@RequestBody ValidationMutantInDTO validation){
        return mutantService.validateMutant(validation);
    }

    @GetMapping(path = "stats")
    public StatsOutDTO getStats(){
        return new StatsOutDTO(1,1,1.0);// TODO: servicio obtener stats
    }
}
