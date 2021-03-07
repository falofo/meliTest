package com.meli.test.service;

import com.meli.test.models.dto.ValidateDTO;
import com.meli.test.models.dto.ValidationMutantInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS;
import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT;
import static com.meli.test.util.SequenceUtils.*;

@Service
public class MutantService {

    /**
     *
     * @param validation
     * @return
     */
    public ResponseEntity validateMutant(ValidationMutantInDTO validation){
        if(isMutant(validation.getDna())){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    /**
     *
     * @param dna
     * @return
     */
    private boolean isMutant(String[] dna){
        ValidateDTO val = new ValidateDTO();
        int count = 0;
        count = countMatch(String.join("|",dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){
            return true;
        }

        count = countMatch(getVertical(dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){
            return true;
        }

        count = countMatch(getRightLeftDiagonals(dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){
            return true;
        }

        count = countMatch(getLeftRightDiagonals(dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){
            return true;
        }


        return false;
    }

    /**
     *
     * @param sequence
     * @return
     */
    private int countMatch(String sequence, int baseCount){
        String regex = String.format("A{%d}|T{%d}|G{%d}|C{%d}",NUMBER_OF_CONSECUTIVE_LETTERS,NUMBER_OF_CONSECUTIVE_LETTERS,NUMBER_OF_CONSECUTIVE_LETTERS,NUMBER_OF_CONSECUTIVE_LETTERS);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sequence);
        int count = baseCount;
        while (count < NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT && matcher.find()) {
            count++;
        }

        return count;
    }

}
