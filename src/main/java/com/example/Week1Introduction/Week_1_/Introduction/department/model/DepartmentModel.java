package com.example.Week1Introduction.Week_1_.Introduction.department.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Department")
@Entity
public class DepartmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer departmentId;

    private String title;
    private Boolean active;
    private LocalDateTime creationAt;
}
