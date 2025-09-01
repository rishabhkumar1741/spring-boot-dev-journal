package com.example.Week1Introduction.Week_1_.Introduction.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {
    private String field;
    private String message;
    private String code;
}
