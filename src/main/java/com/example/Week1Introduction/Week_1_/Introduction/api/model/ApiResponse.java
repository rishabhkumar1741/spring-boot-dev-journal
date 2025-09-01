package com.example.Week1Introduction.Week_1_.Introduction.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;
    private List<ErrorDetail> errors;
    private String path;
    private OffsetDateTime timestamp;

    // Static builder of Success
    public static <T> ApiResponse<T> success (T data, String message)
    {
        ApiResponse<T> res = new ApiResponse<>();
        res.success= true;
        res.code= "OK";
        res.message = (message!=null ? message:"Request Successful");
        res.data = data;
        res.timestamp = OffsetDateTime.now();
        return res;
    }

    // Static builder of Error
    public static <T> ApiResponse<T> error(String code, String message, List<ErrorDetail> errors)
    {
        ApiResponse<T> res = new ApiResponse<>();
        res.success=false;
        res.code = code;
        res.message = message;
        res.errors = errors;
        res.timestamp = OffsetDateTime.now();
        return res;
    }

}
