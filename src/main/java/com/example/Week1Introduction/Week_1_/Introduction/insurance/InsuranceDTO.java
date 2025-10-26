package com.example.Week1Introduction.Week_1_.Introduction.insurance;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDTO {
    @NotNull
    private String policyNumber;
    @NotNull
    private String provider;
    @NotNull
    private Integer userId;
    @NotNull(message = "enter a expire date")
    private LocalDate validUntil;
}
