package com.example.Week1Introduction.Week_1_.Introduction.common.interfaces;

import java.time.LocalDate;

public interface PatientView {
    Long getPatientId();
    String getName();
    String getGender();
    LocalDate getBirthDate();
    String getEmail();
    String getBloodGroup();
}
