package com.microservices.numerology.app.cmd;

import com.microservices.numerology.domain.enums.NumerologyType;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateNumerologyCmd {
    private long id;

    private int number;

    private NumerologyType type;

    private String description;
}
