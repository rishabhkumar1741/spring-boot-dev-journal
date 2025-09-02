package com.example.Week1Introduction.Week_1_.Introduction.api.model;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    OK(HttpStatus.OK, "Request successful"),
    CREATED(HttpStatus.CREATED, "Resource created"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "No content"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "One or more fields are invalid."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Authentication is required."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "You don't have permission to perform this action."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not allowed."),
    CONFLICT(HttpStatus.CONFLICT, "Conflict with current state."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type."),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "Not acceptable"),
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "Payload too large"),
    RATE_LIMITED(HttpStatus.TOO_MANY_REQUESTS, "Too many requests"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "Service temporarily unavailable");


    private final HttpStatus httpStatus;
    private final String detaultMessage;


    ErrorCode(HttpStatus httpStatus,String detaultMessage)
    {
        this.httpStatus = httpStatus;
        this.detaultMessage = detaultMessage;
    }

    public HttpStatus getHttpStatus()
    {
        return this.httpStatus;
    }

    public String getDetaultMessage()
    {
        return this.detaultMessage;
    }



}
