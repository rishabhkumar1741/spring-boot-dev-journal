package com.example.Week1Introduction.Week_1_.Introduction.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
uniqueConstraints = {@UniqueConstraint(name = "name_email",columnNames = {"name","email"})},
        indexes = {@Index(name= "emp_id",columnList = "employeeId")}
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId; // prefer camelCase field names
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String role;

    private String email;

    private LocalDateTime creationAt;
    private boolean active;
    private Long phoneNumber;
}
