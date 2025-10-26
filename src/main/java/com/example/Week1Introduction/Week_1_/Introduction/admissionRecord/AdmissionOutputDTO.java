package com.example.Week1Introduction.Week_1_.Introduction.admissionRecord;

import com.example.Week1Introduction.Week_1_.Introduction.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionOutputDTO {
    private Integer id;
    private Integer fees;
    private Student student;

    public AdmissionOutputDTO(AdmissionRecord admissionRecord )
    {
        this.id = admissionRecord.getId();
        this.fees = admissionRecord.getFees();
        if(admissionRecord.getStudent() != null)
        {
            this.student= admissionRecord.getStudent();
            this.student.setAdmissionRecord(null);
        }else {
            this.student = null;
        }

    }
}
