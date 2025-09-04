package com.example.Week1Introduction.Week_1_.Introduction.department.service;

import com.example.Week1Introduction.Week_1_.Introduction.department.model.DepartmentDTO;
import com.example.Week1Introduction.Week_1_.Introduction.department.model.DepartmentModel;
import com.example.Week1Introduction.Week_1_.Introduction.department.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper)
    {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartment() {
        try {
            List<DepartmentModel> data = departmentRepository.findAll();
            return data.stream().map((x)->modelMapper.map(x,DepartmentDTO.class)).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DepartmentDTO postDepartment(DepartmentDTO departmentDTO) {
        try{
            return modelMapper.map(this.departmentRepository.save(modelMapper.map(departmentDTO, DepartmentModel.class)),DepartmentDTO.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
