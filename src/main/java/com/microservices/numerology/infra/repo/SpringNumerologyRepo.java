package com.microservices.numerology.infra.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.numerology.domain.Numerology;
import com.microservices.numerology.domain.enums.NumerologyType;


@Repository
public interface SpringNumerologyRepo extends JpaRepository<Numerology, Long> {

    Optional<Numerology> findFirstByNumberAndType(int number, NumerologyType type);
}
