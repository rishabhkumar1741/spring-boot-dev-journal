package com.example.Week1Introduction.Week_1_.Introduction.DTO;

import com.example.Week1Introduction.Week_1_.Introduction.Annotations.EmployeeRoleValidationAnnotation;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Name cannot be Empty")
    private String name ;
    @EmployeeRoleValidationAnnotation
    private String role;
    @Email(message = "email should be valid email")
    @Size(min = 3,max = 15,message = "Length should we range between 3 to 10")
    private String email;
    private LocalDateTime creationAt;


    @Positive
    private Integer phoneNumber;


    public EmployeeDTO(String name, String role, String email, LocalDateTime creationAt) {
        this.name = name;
        this.role = role;
        this.creationAt = creationAt;
    }
}
