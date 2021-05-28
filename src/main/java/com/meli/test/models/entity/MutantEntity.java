package com.meli.test.models.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "mutant")
@Data
public class MutantEntity {
    private String id;
    private boolean mutant;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    @Column(name = "mutant")
    public boolean isMutant() {
        return mutant;
    }
}
