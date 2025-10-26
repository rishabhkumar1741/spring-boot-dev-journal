package com.example.Week1Introduction.Week_1_.Introduction.patient;

import com.example.Week1Introduction.Week_1_.Introduction.insurance.InsuranceModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class PatientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer patientId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String email;
    private LocalDate dateOfBirth;
    @CreationTimestamp
    private LocalDateTime CreatedAt;
    @LastModifiedDate
    private LocalDateTime lastModify;

    @OneToOne(mappedBy = "patientId")
    private InsuranceModel insuranceModel;

}
