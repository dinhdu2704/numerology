package com.microservices.numerology.infra.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
public class ApiError {

    private final List<ApiErrorEntry> errors;

    public ApiError(ApiErrorEntry... errorEntries) {
        errors = Arrays.asList(errorEntries);
    }

    public ApiError(List<ApiErrorEntry> apiErrorEntries) {
        errors = apiErrorEntries;
    }

    @Accessors(chain = true)
    @Data
    public static class ApiErrorEntry {

        private String errorCode;

        private String errorMessage;

        private String errorTitle;
    }
}
