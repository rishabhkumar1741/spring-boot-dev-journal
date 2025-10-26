package com.example.Week1Introduction.Week_1_.Introduction.insurance;

import com.example.Week1Introduction.Week_1_.Introduction.patient.PatientModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceResponseDTO {
    private Integer insuranceId;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
    private LocalDateTime createAt;
    private PatientModel patientId;
}
