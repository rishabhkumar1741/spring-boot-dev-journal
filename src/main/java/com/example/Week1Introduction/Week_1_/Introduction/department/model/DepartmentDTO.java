package com.example.Week1Introduction.Week_1_.Introduction.department.model;

import com.example.Week1Introduction.Week_1_.Introduction.common.annotation.PasswordAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private String title;
    private Boolean active;

    private Integer primeNumber;
    @PasswordAnnotation
    @JsonProperty("Password")
    private String Password;
}
