package com.example.Week1Introduction.Week_1_.Introduction.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<PatientModel,Integer> {

}
