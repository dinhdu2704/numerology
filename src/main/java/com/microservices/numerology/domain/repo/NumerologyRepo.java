package com.microservices.numerology.domain.repo;

import java.util.Optional;

import com.microservices.numerology.domain.Numerology;
import com.microservices.numerology.domain.enums.NumerologyType;

public interface NumerologyRepo {

    Numerology save(Numerology numerology);

    Optional<Numerology> findById(long id);

    Optional<Numerology> findBy(int number, NumerologyType type);
}
