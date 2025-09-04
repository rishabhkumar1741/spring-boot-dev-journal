package com.example.Week1Introduction.Week_1_.Introduction.department.repository;

import com.example.Week1Introduction.Week_1_.Introduction.department.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentModel,Integer> {
}
