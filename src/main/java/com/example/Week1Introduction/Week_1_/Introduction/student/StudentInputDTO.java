package com.example.Week1Introduction.Week_1_.Introduction.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDTO {
    @Size(min = 3,message = "Name must be greater than or equal to 3 characters")
    private String name;
    @NotNull
    private String email;
}
