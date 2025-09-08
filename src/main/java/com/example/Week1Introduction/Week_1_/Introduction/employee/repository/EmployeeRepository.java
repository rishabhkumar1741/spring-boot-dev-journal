package com.example.Week1Introduction.Week_1_.Introduction.employee.repository;

import com.example.Week1Introduction.Week_1_.Introduction.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByNameAndEmail(String name, String email);
    List<Employee> findByNameLikeAndEmailLike(String name, String email);
    List<Employee> findByNameLikeOrEmailLike(String name, String email);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%', :name, '%') OR e.email LIKE CONCAT('%', :email, '%')")
    List<Employee>rishabh(@Param("name") String name, @Param("email") String email);
}
