package com.example.Week1Introduction.Week_1_.Introduction.employee.model;



import com.example.Week1Introduction.Week_1_.Introduction.common.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @Size(min = 3)
    private String name;
    @EmployeeRoleValidation
    private String role;
    @Size(min = 3)
    private String email;
    private boolean active;
    private Long phoneNumber;

}
