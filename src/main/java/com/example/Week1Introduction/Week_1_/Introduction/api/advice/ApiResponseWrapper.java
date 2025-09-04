package com.example.Week1Introduction.Week_1_.Introduction.api.advice;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // skip if already ApiResponse (error case)
        if(body instanceof ApiResponse) return body;

        ApiResponse<Object> res = ApiResponse.success(body,"Request successful");
        res.setPath(((ServletServerHttpRequest) request).getServletRequest().getRequestURI());
        int code = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
        HttpStatus statusCode = HttpStatus.valueOf(code);
        res.setTimestamp(OffsetDateTime.now());
        return new ResponseEntity<>(res,statusCode);
    }
}
