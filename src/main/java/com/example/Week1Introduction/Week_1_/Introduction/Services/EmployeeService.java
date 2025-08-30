package com.example.Week1Introduction.Week_1_.Introduction.Services;

import com.example.Week1Introduction.Week_1_.Introduction.DTO.CustomResponseMessage;
import com.example.Week1Introduction.Week_1_.Introduction.DTO.EmployeeDTO;
import com.example.Week1Introduction.Week_1_.Introduction.Entities.EmployeeEntity;
import com.example.Week1Introduction.Week_1_.Introduction.Repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;



    EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper)
    {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public boolean isEmployeeExistById(int id)
    {
        return employeeRepository.existsById(id);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> listOfEmployee =  employeeRepository.findAll();
        return listOfEmployee.stream().map((x->modelMapper.map(x,EmployeeDTO.class))).toList();
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity emp =  employeeRepository.save(new EmployeeEntity(true, LocalDateTime.now(),employeeDTO.getEmail(),employeeDTO.getRole(),employeeDTO.getName()));
        return new EmployeeDTO(emp.getName(),emp.getRole(),emp.getEmail(),emp.getCreationAt()) ;
    }

    public EmployeeDTO getEmployeeById(int emp) {
        Optional<EmployeeEntity> obj = employeeRepository.findById(emp);
        return obj.map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).orElse(null);
    }

    public void deleteDataById(int Empid) {
        employeeRepository.deleteById(Empid);
    }

    public EmployeeDTO updateEmployeeById(Integer empid, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findById(empid)
                .map(existingEmp -> {
                    // update existing
                    existingEmp.setName(employeeDTO.getName());
                    existingEmp.setRole(employeeDTO.getRole());
                    existingEmp.setEmail(employeeDTO.getEmail());
                    existingEmp.setCreationAt(LocalDateTime.now());
                    return existingEmp;
                })
                .orElseGet(() -> {
                    // insert new
                    EmployeeEntity newEmp = modelMapper.map(employeeDTO, EmployeeEntity.class);
                    newEmp.setCreationAt(LocalDateTime.now());
                    return newEmp;
                });

        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updatePatialEmployeeById(Integer id, Map<String,Object> updates) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            return null;
        } else
        {
            updates.forEach((field,value)->{
                Field fieldToUpdate = ReflectionUtils.findField(EmployeeEntity.class,field);
                if(fieldToUpdate!=null)
                {
                    fieldToUpdate.setAccessible(true);
                    ReflectionUtils.setField(fieldToUpdate,employeeEntity,value);
                }
            });
        }
        EmployeeEntity updateEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(updateEntity,EmployeeDTO.class);
    }
}
