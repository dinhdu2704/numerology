package com.microservices.numerology.infra.rest.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.microservices.numerology.domain.enums.NumerologyType;

import lombok.Data;

@Data
public class CreateNumerologyRequest {

    @NotNull
    private int number;

    @NotNull
    private NumerologyType type;

    @NotBlank
    private String description;
}
