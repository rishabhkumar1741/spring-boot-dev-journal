package com.example.Week1Introduction.Week_1_.Introduction.employee.controller;


import com.example.Week1Introduction.Week_1_.Introduction.common.exception.EmployeeCreationException;
import com.example.Week1Introduction.Week_1_.Introduction.employee.model.EmployeeDTO;
import com.example.Week1Introduction.Week_1_.Introduction.employee.service.EmployeeService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
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

    @GetMapping(path = "/test")
    public List<EmployeeDTO> test()
    {
        System.out.println("Rishabh");
        if(employeeService.test()!=null)
        {
            return employeeService.test();
        }else {
            throw  new NoSuchElementException("No Data Found");
        }
    }

    @DeleteMapping(path ={"/{id}"} )
    public Boolean delete(@PathVariable Long id) throws Exception {
        return this.employeeService.delete(id);
    }
}
