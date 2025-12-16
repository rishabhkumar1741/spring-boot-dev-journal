package com.example.Week1Introduction.Week_1_.Introduction.patient;

import com.example.Week1Introduction.Week_1_.Introduction.insurance.InsuranceModel;
import com.example.Week1Introduction.Week_1_.Introduction.system.QC_EGMS_USERS;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientRepo patientRepo;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<PatientModel> getPatient()
    {
        QC_EGMS_USERS users = (QC_EGMS_USERS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(users.getUsername() + " username");
        return patientRepo.findAll();
    }
    @PostMapping
    public PatientModel postPatient(@RequestBody @Valid PatientDTO patientDTO){
        PatientModel patientModel =  modelMapper.map(patientDTO,PatientModel.class);
        return patientRepo.save(patientModel);
    }
    @GetMapping(path = "/{id}")
    public PatientModel getPatientById(@PathVariable Integer id)
    {
        Optional<PatientModel> patientModel  = patientRepo.findById(id);
        try {
            if(patientModel.isPresent())
            {
                return  patientModel.get();
            }else {
                throw new RuntimeException("Enter valid Patient Id");
            }
        }catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }
    }
}
