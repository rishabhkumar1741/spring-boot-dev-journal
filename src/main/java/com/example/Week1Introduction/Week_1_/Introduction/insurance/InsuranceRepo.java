package com.example.Week1Introduction.Week_1_.Introduction.insurance;

import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceRepo extends JpaRepository<InsuranceModel, Integer> {


    @Query("Select i from InsuranceModel i JOIN FETCH i.patientId Where i.insuranceId = :id")
    Optional<InsuranceModel> findInsuranceById(@Param("id") Integer id);
}
