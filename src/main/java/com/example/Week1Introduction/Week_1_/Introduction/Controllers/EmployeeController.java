package com.example.Week1Introduction.Week_1_.Introduction.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping(path = "/")
    public String getMyMessage()
    {
        return "rishabh Kumar";
    }

    @GetMapping(path = "/employees/{empId}")
    public String getEmplyee(@PathVariable String empId)
    {
        return "rishabh Kumar "+ empId;
    }

    @GetMapping(path = "/employees")
    public  String getAllEmp(@RequestParam(required = true) Integer age,@RequestParam(required = false) String name)
    {
        return "All Emp Data "+ age +" name "+name;
    }
}
