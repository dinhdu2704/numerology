package com.microservices.numerology.infra.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.numerology.app.cmd.CalculateNumerologyCmd;
import com.microservices.numerology.app.svc.NumerologyAppSvc;
import com.microservices.numerology.infra.rest.req.NumerologyRequest;

@RestController
public class NumerologyController {

    private final NumerologyAppSvc numerologyAppSvc;

    public NumerologyController(NumerologyAppSvc numerologyAppSvc) {
        this.numerologyAppSvc = numerologyAppSvc;
    }

    @PostMapping(value = "/calculate")
    public Object calculate(@RequestBody @Valid NumerologyRequest request) {
        var res = numerologyAppSvc.calculate(toCmd(request));

        return res;
    }

    private CalculateNumerologyCmd toCmd(NumerologyRequest request) {
        return new CalculateNumerologyCmd()
                .setFullName(request.getName())
                .setDateOfBirth(request.getDateOfBirth());
    }
}
