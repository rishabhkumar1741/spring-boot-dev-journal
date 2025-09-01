package com.example.Week1Introduction.Week_1_.Introduction.employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employee_Id;
    private String name;
    private String role;
    private String email;
    private LocalDateTime creationAt;
    private boolean active;
    private Long phoneNumber;

}
