package com.meli.test.repositories;

import com.meli.test.models.entity.MutantEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;


@EnableScan
@EnableScanCount
public interface MutantRepository extends CrudRepository<MutantEntity, String> {

    long countByMutant(boolean mutant);
}
