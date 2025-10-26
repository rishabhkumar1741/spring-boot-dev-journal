package com.example.Week1Introduction.Week_1_.Introduction.insurance;

import com.example.Week1Introduction.Week_1_.Introduction.patient.PatientModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance")
public class InsuranceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer insuranceId;
    @Column(nullable = false,unique = true)
    private String policyNumber;
    @Column(nullable = false)
    private String provider;
    @Column(nullable = false)
    private LocalDate validUntil;
    @CreationTimestamp
    private LocalDateTime createAt;
    @OneToOne
    @JoinColumn(name = "patientId",nullable = false)
    @JsonBackReference
    private PatientModel patientId;

}
