package com.example.Week1Introduction.Week_1_.Introduction.employee.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/employees")
public class EmployeeController {

    @GetMapping
    public String v1employee()
    {
        return "rishabh";
    }

}
