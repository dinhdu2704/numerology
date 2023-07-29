package com.microservices.numerology.infra.rest.req;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NumerologyRequest {

    @NotBlank
    private String name;

    @NotBlank
    private LocalDate dateOfBirth;
}
