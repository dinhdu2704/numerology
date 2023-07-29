package com.microservices.numerology.app.svc;

import com.microservices.numerology.app.cmd.CalculateNumerologyCmd;
import com.microservices.numerology.app.cmd.CreateNumerologyCmd;
import com.microservices.numerology.domain.Numerology;

public interface NumerologyAppSvc {

    Object calculate(CalculateNumerologyCmd cmd);

    Numerology create(CreateNumerologyCmd cmd);
}
