package com.example.Week1Introduction.Week_1_.Introduction.admissionRecord;

import com.example.Week1Introduction.Week_1_.Introduction.student.Student;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionInputDTO {
    @NotNull(message = "Fees are required")
    private Integer fees;
    @NotNull(message = "student Id is required")
    private Integer student;
}
