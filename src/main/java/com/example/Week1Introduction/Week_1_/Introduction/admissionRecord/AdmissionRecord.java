package com.example.Week1Introduction.Week_1_.Introduction.admissionRecord;

import com.example.Week1Introduction.Week_1_.Introduction.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdmissionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer fees;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Student student;
}
