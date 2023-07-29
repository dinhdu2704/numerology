package com.microservices.numerology.infra.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.numerology.app.cmd.CreateNumerologyCmd;
import com.microservices.numerology.app.svc.NumerologyAppSvc;
import com.microservices.numerology.infra.rest.req.CreateNumerologyRequest;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final NumerologyAppSvc numerologyAppSvc;

    public ManagementController(NumerologyAppSvc numerologyAppSvc) {
        this.numerologyAppSvc = numerologyAppSvc;
    }

    @RequestMapping("/numerology/initiate")
    public Object initiateNumerology(@RequestBody @Valid CreateNumerologyRequest request) {
        return numerologyAppSvc.create(toCmd(request));
    }

    @RequestMapping("/numerology/amend")
    public Object editNumerology(@RequestBody @Valid CreateNumerologyRequest request) {
        return numerologyAppSvc.create(toCmd(request));
    }

    private CreateNumerologyCmd toCmd(CreateNumerologyRequest request) {
        return new CreateNumerologyCmd()
                .setNumber(request.getNumber())
                .setType(request.getType())
                .setDescription(request.getDescription());
    }
}
