package com.example.Week1Introduction.Week_1_.Introduction.DTO;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
public class CustomResponseMessage<T> {
    private final boolean success;
    private final String message;
    private final T data;
    private final HttpStatus statusCode;
    private final LocalDateTime timestamp;

    public CustomResponseMessage(boolean success, String message, T data, HttpStatus statusCode, LocalDateTime timestamp) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }
}
