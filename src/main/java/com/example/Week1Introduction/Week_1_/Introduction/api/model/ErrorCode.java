package com.example.Week1Introduction.Week_1_.Introduction.api.model;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    OK(HttpStatus.OK,"Request successful");
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
