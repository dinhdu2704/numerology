package com.microservices.numerology.app.svc;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.microservices.numerology.app.cmd.CalculateNumerologyCmd;
import com.microservices.numerology.app.cmd.CreateNumerologyCmd;
import com.microservices.numerology.domain.Numerology;
import com.microservices.numerology.domain.exception.DomainCode;
import com.microservices.numerology.domain.exception.DomainException;
import com.microservices.numerology.domain.repo.NumerologyRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StdNumerologyAppSvc implements NumerologyAppSvc {

    private final NumerologyRepo numerologyRepo;


    public StdNumerologyAppSvc(NumerologyRepo numerologyRepo) {
        this.numerologyRepo = numerologyRepo;
    }

    @Override
    public Object calculate(CalculateNumerologyCmd cmd) {
        return null;
    }

    @Override
    public Numerology create(CreateNumerologyCmd cmd) {
        log.info("method: createNumerology - cmd: {}", cmd);

        var numerology = numerologyRepo
            .findBy(cmd.getNumber(), cmd.getType())
            .map(n -> updateNumerology(cmd, n))
            .orElse(createNumerology(cmd));

        log.info("method: createNumerology - numerology: {}", numerology);

        return numerology;
    }

    public Numerology createNumerology(CreateNumerologyCmd cmd) {
        var numerology = toNumerology(cmd);

        return numerologyRepo.save(numerology);
    }

    public Numerology updateNumerology(CreateNumerologyCmd cmd, Numerology numerology) {
        if (!numerology.getId().equals(cmd.getId())) {
            throw new DomainException(DomainCode.DUPLICATED_NUMEROLOGY, Map.of("existNumerology", numerology));
        }

        return numerologyRepo.save(toNumerology(cmd));
    }

    private Numerology toNumerology(CreateNumerologyCmd cmd) {
        return new Numerology()
            .setNumber(cmd.getNumber())
            .setType(cmd.getType())
            .setDescription(cmd.getDescription());
    }


}
