package com.microservices.numerology.infra.repo;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.microservices.numerology.domain.Numerology;
import com.microservices.numerology.domain.enums.NumerologyType;
import com.microservices.numerology.domain.repo.NumerologyRepo;

@Repository
public class JpaNumerologyRepo implements NumerologyRepo {

    private final SpringNumerologyRepo springNumerologyRepo;

    public JpaNumerologyRepo(SpringNumerologyRepo springNumerologyRepo) {
        this.springNumerologyRepo = springNumerologyRepo;
    }

    @Override
    public Numerology save(Numerology numerology) {
        return springNumerologyRepo.save(numerology);
    }

    @Override
    public Optional<Numerology> findById(long id) {
        return springNumerologyRepo.findById(id);
    }

    @Override
    public Optional<Numerology> findBy(int number, NumerologyType type) {
        return springNumerologyRepo.findFirstByNumberAndType(number, type);
    }
}
