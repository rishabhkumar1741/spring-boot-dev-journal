package com.example.Week1Introduction.Week_1_.Introduction.employee.service;

import com.example.Week1Introduction.Week_1_.Introduction.employee.model.Employee;
import com.example.Week1Introduction.Week_1_.Introduction.employee.model.EmployeeDTO;
import com.example.Week1Introduction.Week_1_.Introduction.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper)
    {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> data = new ArrayList<>();
        for (Employee x : this.employeeRepository.findAll()) {
            EmployeeDTO map = modelMapper.map(x, EmployeeDTO.class);
            data.add(map);
        }
        return data;
    }

    public EmployeeDTO postEmployee(EmployeeDTO employeeDTO)  {
        Employee emp = modelMapper.map(employeeDTO,Employee.class);
        emp.setCreationAt(LocalDateTime.now());

        return modelMapper.map(this.employeeRepository.save(emp),EmployeeDTO.class);
    }

    public List<EmployeeDTO> test() {
        List<Employee> obj = employeeRepository.rishabh("Rishabh","R");
        if(obj.isEmpty() )
        {
            throw new NoSuchElementException("No element found");
        }

        return obj.stream().map(x->  modelMapper.map(x,EmployeeDTO.class)).toList();

    }

    public Boolean delete(Long id) throws Exception {
         try{
             this.employeeRepository.deleteById(id);
             return true;
         }catch (Exception e)
         {
             throw new Exception(e.getMessage());
         }

    }
}
