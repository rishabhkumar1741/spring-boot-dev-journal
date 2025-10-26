package com.example.Week1Introduction.Week_1_.Introduction.student;

import com.example.Week1Introduction.Week_1_.Introduction.admissionRecord.AdmissionRecord;

import com.example.Week1Introduction.Week_1_.Introduction.course.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDTO {
    private Integer id;
    private String name;
    private AdmissionRecord admissionRecord;
    private Set<Course> courseList;

    public StudentOutputDTO(Student student)
    {
        this.id = student.getId();
        this.name = student.getName();
        this.admissionRecord = student.getAdmissionRecord();
        if(this.admissionRecord!=null)
        {
            this.admissionRecord.setStudent(null);
        }
        if(this.courseList!=null)
        {
            this.courseList = student.getCourse();
        }
    }
}
