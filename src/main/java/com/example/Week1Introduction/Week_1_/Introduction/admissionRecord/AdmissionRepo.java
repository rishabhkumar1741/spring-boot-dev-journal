package com.example.Week1Introduction.Week_1_.Introduction.admissionRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepo extends JpaRepository<AdmissionRecord,Integer> {
    @Query("SELECT a from AdmissionRecord a INNER JOIN a.student")
    List<AdmissionRecord> findAllAdmission();
}
