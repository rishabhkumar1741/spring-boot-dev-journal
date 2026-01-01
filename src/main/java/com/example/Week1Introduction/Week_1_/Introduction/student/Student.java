package com.example.Week1Introduction.Week_1_.Introduction.student;

import com.example.Week1Introduction.Week_1_.Introduction.admissionRecord.AdmissionRecord;
import com.example.Week1Introduction.Week_1_.Introduction.course.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private AdmissionRecord  admissionRecord;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable()
    private Set<Course> course = new HashSet<>();
}
