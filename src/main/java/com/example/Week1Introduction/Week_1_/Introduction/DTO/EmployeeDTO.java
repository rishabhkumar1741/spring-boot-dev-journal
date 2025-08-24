package com.example.Week1Introduction.Week_1_.Introduction.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name ;
    private String role;
    private String email;
    private LocalDateTime creationAt;


}
