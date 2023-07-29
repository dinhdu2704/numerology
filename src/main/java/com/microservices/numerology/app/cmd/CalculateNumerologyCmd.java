package com.microservices.numerology.app.cmd;

import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CalculateNumerologyCmd {

    private String fullName;

    private LocalDate dateOfBirth;
}
