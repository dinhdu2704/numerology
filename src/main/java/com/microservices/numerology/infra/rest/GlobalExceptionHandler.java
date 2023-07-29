package com.microservices.numerology.infra.rest;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.microservices.numerology.domain.exception.DomainCode;
import com.microservices.numerology.domain.exception.DomainException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    private final MessageSource titleSource;

    public GlobalExceptionHandler(MessageSource messageSource, MessageSource titleSource) {
        this.messageSource = messageSource;
        this.titleSource = titleSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiError missingServletRequestParameterException(
        HttpServletRequest request,
        MissingServletRequestParameterException e) {
        log.warn(
            "method: missingServletRequestParameterException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.MISSING_SERVLET_REQUEST_PARAMETER, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError httpMessageNotReadableException(
        HttpServletRequest request,
        HttpMessageNotReadableException e) {
        log.warn(
            "method: httpMessageNotReadableException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.HTTP_MESSAGE_NOT_READABLE, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiError methodArgumentTypeMismatchException(
        HttpServletRequest request,
        MethodArgumentTypeMismatchException e) {
        log.warn(
            "method: methodArgumentTypeMismatchException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.METHOD_ARGUMENT_TYPE_MISMATCH, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiError httpMediaTypeNotSupportedException(
        HttpServletRequest request,
        HttpMediaTypeNotSupportedException e) {
        log.warn(
            "method: httpMediaTypeNotSupportedException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiError httpRequestMethodNotSupportedException(
        HttpServletRequest request,
        HttpRequestMethodNotSupportedException e) {
        log.warn(
            "method: httpRequestMethodNotSupportedException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ApiError missingRequestHeaderException(
        HttpServletRequest request,
        MissingRequestHeaderException e) {
        log.warn(
            "method: missingRequestHeaderException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.MISSING_REQUEST_HEADER, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    public ApiError unexpectedTypeException(
        HttpServletRequest request,
        UnexpectedTypeException e) {
        log.warn(
            "method: missingRequestHeaderException - endPoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.MISSING_REQUEST_HEADER, null, null));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DomainException.class)
    public ApiError domainException(
        HttpServletRequest request,
        DomainException e) {
        log.warn("method: domainException - endpoint: {} - queryString: {}",
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(e.getDomainCode(), null, e.getArgs()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError exception(
        HttpServletRequest request,
        Exception e) {
        log.error("method: exception - saId: {} - endpoint: {} - queryString: {}",
            request.getHeader("saId"),
            request.getRequestURI(),
            request.getQueryString(),
            e);

        return new ApiError(createApiErrorEntry(DomainCode.UNKNOWN_ERROR, null, null));
    }

    private ApiError.ApiErrorEntry createApiErrorEntry(DomainCode domainCode, Object[] titleArgs, Object[] msgArgs) {
        return new ApiError.ApiErrorEntry()
            .setErrorCode(domainCode.getValue())
            .setErrorTitle(titleSource.getMessage(
                domainCode.getValue(),
                titleArgs,
                Locale.getDefault()))
            .setErrorMessage(messageSource.getMessage(
                domainCode.getValue(),
                msgArgs,
                Locale.getDefault()));
    }
}