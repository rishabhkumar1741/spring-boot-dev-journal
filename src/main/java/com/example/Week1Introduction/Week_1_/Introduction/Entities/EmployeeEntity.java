package com.example.Week1Introduction.Week_1_.Introduction.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String name ;
    private String role;
    private String email;
    private LocalDateTime creationAt;
    private Boolean isAcive ;

    public EmployeeEntity(Boolean isAcive, LocalDateTime creationAt, String email, String role, String name) {
        this.isAcive = isAcive;
        this.creationAt = creationAt;
        this.email = email;
        this.role = role;
        this.name = name;
    }
}
