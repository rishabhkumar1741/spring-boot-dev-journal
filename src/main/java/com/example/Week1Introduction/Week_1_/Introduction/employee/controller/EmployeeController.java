package com.example.Week1Introduction.Week_1_.Introduction.employee.controller;


import com.example.Week1Introduction.Week_1_.Introduction.employee.model.EmployeeDTO;
import com.example.Week1Introduction.Week_1_.Introduction.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees()
    {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO postEmployee(@RequestBody @Valid EmployeeDTO employeeDTO)
    {
        return this.employeeService.postEmployee(employeeDTO);
    }

}
