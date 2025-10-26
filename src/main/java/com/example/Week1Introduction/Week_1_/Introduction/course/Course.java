package com.example.Week1Introduction.Week_1_.Introduction.course;

import com.example.Week1Introduction.Week_1_.Introduction.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String title;

    @ManyToMany(mappedBy = "course",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> student = new HashSet<>();

}
