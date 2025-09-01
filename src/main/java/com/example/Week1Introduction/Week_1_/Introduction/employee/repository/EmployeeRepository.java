package com.example.Week1Introduction.Week_1_.Introduction.employee.repository;

import com.example.Week1Introduction.Week_1_.Introduction.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
