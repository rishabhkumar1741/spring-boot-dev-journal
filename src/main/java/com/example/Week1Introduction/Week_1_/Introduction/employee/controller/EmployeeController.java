package com.example.Week1Introduction.Week_1_.Introduction.employee.controller;


import com.example.Week1Introduction.Week_1_.Introduction.common.exception.EmployeeCreationException;
import com.example.Week1Introduction.Week_1_.Introduction.employee.model.EmployeeDTO;
import com.example.Week1Introduction.Week_1_.Introduction.employee.service.EmployeeService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<EmployeeDTO> postEmployee(@RequestBody @Valid EmployeeDTO employeeDTO)
    {
         EmployeeDTO data =  this.employeeService.postEmployee(employeeDTO);
        return new ResponseEntity<>(data,HttpStatus.CREATED);
    }

    @GetMapping(path = "/{empid}")
    public ResponseEntity getEmployeeByid(@PathVariable int empid)
    {
        System.out.println("=================== "+ empid);
        throw new NoSuchElementException() ;

    }

}
