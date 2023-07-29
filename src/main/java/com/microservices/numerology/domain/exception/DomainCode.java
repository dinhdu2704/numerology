package com.microservices.numerology.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomainCode {
    // Technical errors
    UNKNOWN_ERROR("000"),
    INVALID_INPUT_FIELD("001"),
    MISSING_SERVLET_REQUEST_PARAMETER("002"),
    HTTP_MESSAGE_NOT_READABLE("003"),
    METHOD_ARGUMENT_TYPE_MISMATCH("004"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("005"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("006"),
    MISSING_REQUEST_HEADER("007"),
    UNEXPECTED_TYPE("008"),

    // Business errors
    DUPLICATED_NUMEROLOGY("100");

    private final String value;


}
