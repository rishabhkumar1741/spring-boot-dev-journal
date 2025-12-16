package com.example.Week1Introduction.Week_1_.Introduction.api.error;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import com.example.Week1Introduction.Week_1_.Introduction.api.model.ErrorCode;
import com.example.Week1Introduction.Week_1_.Introduction.api.model.ErrorDetail;
import com.example.Week1Introduction.Week_1_.Introduction.common.exception.NotFoundError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static HashMap<String,String> getPath(HttpServletRequest request,HttpServletResponse response)
    {
        HashMap<String,String> urldata = new HashMap<>(); ;
        urldata.put("url",request.getRequestURI());
        urldata.put("code",String.valueOf(response.getStatus()));
        return urldata;
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleallexceptions(MethodArgumentNotValidException e, HttpServletRequest req)
    {
        List<ErrorDetail> details = e.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ErrorDetail(err.getField(),err.getDefaultMessage(),err.getCode()))
                .toList();
        ApiResponse<Void> body = ApiResponse.error(ErrorCode.VALIDATION_ERROR.name(), "Invalid input provided",details);
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidFormat(HttpMessageNotReadableException e, HttpServletRequest req) {
        Throwable cause = e.getCause();

        if (cause instanceof InvalidFormatException invalidFormatEx) {
            // Check if the field that failed is dateOfBirth (optional)
            String fieldName = invalidFormatEx.getPath().isEmpty() ? "unknown" : invalidFormatEx.getPath().get(0).getFieldName();
            if ("dateOfBirth".equals(fieldName)) {
                ErrorDetail detail = new ErrorDetail(fieldName, "Invalid date format. Expected 'yyyy-MM-dd'", "InvalidFormat");
                ApiResponse<Void> body = ApiResponse.error(ErrorCode.VALIDATION_ERROR.name(), "Invalid input provided", List.of(detail));
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }
        }

        // Generic fallback
        ErrorDetail detail = new ErrorDetail("dateOfBirth", "Invalid request format", "InvalidFormat");
        ApiResponse<Void> body = ApiResponse.error(ErrorCode.VALIDATION_ERROR.name(), "Invalid input provided", List.of(detail));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> runtimeException(RuntimeException ex)
    {
        return  new ResponseEntity<>(ApiResponse.error(ErrorCode.BAD_REQUEST.name(), ex.getMessage(), null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<ApiResponse> notFoundError(RuntimeException ex)
    {
        return new ResponseEntity<>(ApiResponse.error(ErrorCode.BAD_REQUEST.name(), ex.getMessage(), null),HttpStatus.BAD_REQUEST);

    }



}
