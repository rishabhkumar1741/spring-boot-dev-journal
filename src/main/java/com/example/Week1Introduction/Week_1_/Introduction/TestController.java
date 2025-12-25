package com.example.Week1Introduction.Week_1_.Introduction;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/{name}")
    public String test(HttpServletRequest request, @PathVariable String name){
        return name;
    }
}
