package com.example.Week1Introduction.Week_1_.Introduction.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ErrorDetail {
    private String field;
    private String message;
    private String code;
}
