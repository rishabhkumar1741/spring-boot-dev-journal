package com.example.Week1Introduction.Week_1_.Introduction.HealthCheck;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckControver {

    @GetMapping
    ApiResponse<String> healthCheck()
    {
        return ApiResponse.success(null,"successful");
    }
}
