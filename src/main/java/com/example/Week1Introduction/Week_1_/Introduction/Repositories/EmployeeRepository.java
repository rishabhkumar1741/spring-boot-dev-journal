package com.example.Week1Introduction.Week_1_.Introduction.Repositories;

import com.example.Week1Introduction.Week_1_.Introduction.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
}
