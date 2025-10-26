package com.example.Week1Introduction.Week_1_.Introduction.admissionRecord;

import com.example.Week1Introduction.Week_1_.Introduction.student.Student;
import com.example.Week1Introduction.Week_1_.Introduction.student.StudentRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Internal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmissionService {

    public final AdmissionRepo admissionRepo;
    public  final StudentRepo studentRepo;


    @Transactional
    public AdmissionOutputDTO addAdmission(AdmissionInputDTO admissionInputDTO) {
        Optional<Student> student = studentRepo.findByIdStudent(admissionInputDTO.getStudent());
        if (student.isPresent())
        {
            AdmissionRecord admissionRecord = new AdmissionRecord();
            admissionRecord.setStudent(student.get());
            admissionRecord.setFees(admissionInputDTO.getFees());
            AdmissionRecord saved =  admissionRepo.save(admissionRecord);
            return  new AdmissionOutputDTO(saved);

        }else {
            throw new NoSuchElementException("Enter a valid Student Id");
        }
    }

    public List<AdmissionOutputDTO> getAllAdmisiion() {
        return admissionRepo.findAllAdmission().stream().map(x-> new AdmissionOutputDTO(x)).toList();
    }

    public AdmissionOutputDTO getAdmissionById(Integer id) {
        Optional<AdmissionRecord> admissionRecord = admissionRepo.findById(id);
        if(admissionRecord.isPresent())
        {
            return  new AdmissionOutputDTO(admissionRecord.get());
        }else {
            throw new NoSuchElementException("Enter a valid Admission ID");
        }
    }

    @Transactional
    public void deleteByid(Integer id) {
        Optional<AdmissionRecord> admissionRecord = admissionRepo.findById(id);
        if(admissionRecord.isPresent())
        {
            admissionRepo.deleteById(id);
        }else {
            throw new NoSuchElementException("Enter a valid Admission ID");
        }
    }
}
