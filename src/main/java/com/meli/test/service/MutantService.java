package com.meli.test.service;

import com.meli.test.dto.ValidateDTO;
import com.meli.test.dto.ValidationMutantInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(!validateData(dna, val)){
            return false;
        }
        val.setCount(0);
        if(countSequence(dna, val) || countSequence(val.getVertical(), val)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param dna
     * @param val
     * @return
     */
    private boolean validateData(String[] dna, ValidateDTO val){
        Pattern pattern = Pattern.compile("[ATGC]");
        int lengthMatrix = dna.length;
        val.setVertical(new String[lengthMatrix]);
        for (int i = 0; i < lengthMatrix; i++) {
            Matcher matcher = pattern.matcher(dna[i]);
            if(dna[i].length() != lengthMatrix){ //|| !matcher.matches()){
                return false;
            }
            for (int j = 0; j < dna[i].length(); j++) {
                val.getVertical()[j] = (val.getVertical()[j] == null)?dna[i].substring(j,j+1):val.getVertical()[j]+dna[i].substring(j,j+1);
            }
        }
        return true;
    }

    /**
     *
     * @param sequence
     * @param val
     * @return
     */
    private boolean countSequence(String[] sequence, ValidateDTO val){
        for(int i=0;i<sequence.length;i++){
            val.setCount( val.getCount()+countMatch(sequence[i]));
            if(val.getCount() > 1){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param sequence
     * @return
     */
    private int countMatch(String sequence){
        Pattern pattern = Pattern.compile("AAAA|TTTT|GGGG|CCCC");
        Matcher matcher = pattern.matcher(sequence);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }

}
