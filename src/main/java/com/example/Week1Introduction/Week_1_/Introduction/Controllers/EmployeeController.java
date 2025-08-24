package com.example.Week1Introduction.Week_1_.Introduction.Controllers;

import com.example.Week1Introduction.Week_1_.Introduction.DTO.CustomResponseMessage;
import com.example.Week1Introduction.Week_1_.Introduction.DTO.EmployeeDTO;


import com.example.Week1Introduction.Week_1_.Introduction.Services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService service;
    List<EmployeeDTO> emp = new LinkedList<>();

    EmployeeController(EmployeeService service )
    {
        this.service = service;
    }


    public static <T> CustomResponseMessage<T> response(T data, String message,HttpStatus status )
    {
        return new CustomResponseMessage<>(true,message,data,status,LocalDateTime.now());
    }

    @GetMapping
    public ResponseEntity<CustomResponseMessage<List<EmployeeDTO>>> getEmployee()
    {
        return new ResponseEntity<>(response(service.getAllEmployees(),"Employees Data",HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomResponseMessage<EmployeeDTO>> addEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO emp = service.addEmployee(employeeDTO);
        return new ResponseEntity<CustomResponseMessage<EmployeeDTO>>(response(emp,"Employee created successfully",HttpStatus.CREATED),HttpStatus.CREATED);
    }

    @GetMapping(path = "/{empId}")
    public ResponseEntity<CustomResponseMessage<EmployeeDTO>> getEmployeeById(@PathVariable int empId)
    {
        EmployeeDTO emp = service.getEmployeeById(empId);
        return new ResponseEntity<>(response(emp,"Employees Data",HttpStatus.OK),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{Empid}")
    public ResponseEntity<CustomResponseMessage<String>> deleteUser(@PathVariable int Empid)
    {
         service.deleteDataById(Empid);
        return new ResponseEntity<>(response(null, "Employee deleted successfully", HttpStatus.OK),HttpStatus.OK);
    }

    @PutMapping(path = "/{Empid}")
    public ResponseEntity<CustomResponseMessage<EmployeeDTO>> updateEmployeeById(@PathVariable int Empid,@RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO data =  service.updateEmployeeById(Empid,employeeDTO);
        return new ResponseEntity<CustomResponseMessage<EmployeeDTO>>(response(data,"Update Employee Data",HttpStatus.OK),HttpStatus.OK);
    }

    @PatchMapping(path = "/{Empid}")
    public ResponseEntity<CustomResponseMessage<EmployeeDTO>> updatePartialEmployeeBtId(@PathVariable Integer Empid,@RequestBody Map<String,Object> updates)
    {
        EmployeeDTO obj = service.updatePatialEmployeeById(Empid,updates) ;
        return new ResponseEntity<>(response(obj,"Updates Info",HttpStatus.OK),HttpStatus.OK);
    }


}
