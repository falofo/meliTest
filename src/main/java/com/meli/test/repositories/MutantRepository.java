package com.meli.test.repositories;

import com.meli.test.models.entity.MutantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MutantRepository extends CrudRepository<MutantEntity, String> {

    long countByMutant(boolean mutant);
}
