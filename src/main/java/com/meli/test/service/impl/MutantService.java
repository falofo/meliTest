package com.meli.test.service.impl;

import com.meli.test.models.dto.StatsOutDTO;
import com.meli.test.models.entity.MutantEntity;
import com.meli.test.repositories.MutantRepository;
import com.meli.test.service.IMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS;
import static com.meli.test.models.Constants.NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT;
import static com.meli.test.util.SequenceUtils.*;

@Service
public class MutantService implements IMutantService {

    @Autowired
    private MutantRepository mutantRepository;

    /**
     * Valida si un humano es mutante basándose en su secuencia de ADN
     *
     * @param dna
     * @return
     */
    public boolean isMutant(String[] dna){
        int count = 0;
        count = countMatch(String.join("|",dna), count);
        MutantEntity mutant = new MutantEntity();
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){
            mutant.setMutant(true);
            mutantRepository.save(mutant);
            return true;
        }

        count = countMatch(getVertical(dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){mutant.setMutant(true);
            mutantRepository.save(mutant);
            return true;
        }

        count = countMatch(getRightLeftDiagonals(dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){mutant.setMutant(true);
            mutantRepository.save(mutant);
            return true;
        }

        count = countMatch(getLeftRightDiagonals(dna), count);
        if( count == NUMBER_OF_CONSECUTIVE_LETTERS_SEQUENCES_TO_BE_MUTANT){mutant.setMutant(true);
            mutantRepository.save(mutant);
            return true;
        }
        mutantRepository.save(mutant);
        return false;
    }

    /**
     * Cuenta el número de veces donde se detecta más de una secuencia de NUMBER_OF_CONSECUTIVE_LETTERS letras iguales
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

    /**
     * Retorna las estadísticas de las verificaciones de ADN realizadas
     *
     * @return
     */
    public StatsOutDTO getStats(){
        StatsOutDTO out = new StatsOutDTO();
        Long humans = mutantRepository.count();
        Long mutants = mutantRepository.countByMutant(true);
        out.setCountHumanDna(humans.intValue());
        out.setCountMutantDna(mutants.intValue());
        Double ratio = 0D;
        if(humans != 0L) {
            ratio = mutants.doubleValue() / humans.doubleValue();
        }
        out.setRatio(ratio);
        return out;
    }

}
