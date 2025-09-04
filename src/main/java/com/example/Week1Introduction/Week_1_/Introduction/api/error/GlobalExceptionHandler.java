package com.example.Week1Introduction.Week_1_.Introduction.api.error;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import com.example.Week1Introduction.Week_1_.Introduction.api.model.ErrorCode;
import com.example.Week1Introduction.Week_1_.Introduction.api.model.ErrorDetail;
import com.example.Week1Introduction.Week_1_.Introduction.common.exception.EmployeeCreationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.dialect.BooleanDecoder;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static HashMap<String,String> getPath(HttpServletRequest request,HttpServletResponse response)
    {
        HashMap<String,String> urldata = new HashMap<>(); ;
        urldata.put("url",request.getRequestURI());
        urldata.put("code",String.valueOf(response.getStatus()));
        return urldata;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(NoSuchElementException exception, HttpServletRequest req)
    {
        ApiResponse<Void> body = ApiResponse.error(ErrorCode.NOT_FOUND.name(),exception.getMessage(),null);
        body.setPath(req.getRequestURI());
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> runtimeExceptionMethod(RuntimeException runtimeException, HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("RuntimeException ====================================================");
        ApiResponse<Void> body = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.name(),runtimeException.getMessage(),null);
        HashMap<String,String> data = getPath( request,  response);
        body.setPath(data.get("url"));
        body.setCode(data.get("code"));
        return new ResponseEntity<ApiResponse<Void>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
