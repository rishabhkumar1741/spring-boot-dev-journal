package com.example.Week1Introduction.Week_1_.Introduction.department.controller;

import com.example.Week1Introduction.Week_1_.Introduction.department.model.DepartmentDTO;
import com.example.Week1Introduction.Week_1_.Introduction.department.service.DepartmentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/department")
public class DepartmentController {
    private DepartmentService departmentService;
    DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment()
    {
        try{
            List<DepartmentDTO> departments = this.departmentService.getAllDepartment();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> postDepartment(@RequestBody @Valid DepartmentDTO departmentDTO)
    {
        try{
            DepartmentDTO department = this.departmentService.postDepartment(departmentDTO);
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
