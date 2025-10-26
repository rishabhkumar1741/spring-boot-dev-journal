package com.example.Week1Introduction.Week_1_.Introduction.insurance;

import com.example.Week1Introduction.Week_1_.Introduction.common.exception.InvalidPatientId;
import com.example.Week1Introduction.Week_1_.Introduction.patient.PatientDTO;
import com.example.Week1Introduction.Week_1_.Introduction.patient.PatientModel;
import com.example.Week1Introduction.Week_1_.Introduction.patient.PatientRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceRepo insuranceRepo;
    private final PatientRepo patientRepo;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<InsuranceModel> getInsurance()
    {
        return insuranceRepo.findAll();
    }

    @PostMapping
    @Transactional
    public InsuranceModel postInsurance(@RequestBody @Valid InsuranceDTO insuranceDTO){
        Optional<PatientModel> patientModel = patientRepo.findById(insuranceDTO.getUserId());
        if(patientModel.isPresent())
        {
            InsuranceModel insuranceModel =  new InsuranceModel();
            insuranceModel.setPolicyNumber(insuranceDTO.getPolicyNumber());
            insuranceModel.setProvider(insuranceDTO.getProvider());
            insuranceModel.setValidUntil(insuranceDTO.getValidUntil());
            insuranceModel.setPatientId(patientModel.get());
            return insuranceRepo.save(insuranceModel);
        }else {
            throw new InvalidPatientId("Invalid Customer Id");
        }
    }


    @GetMapping(path = "/{id}")
    public InsuranceResponseDTO getInsuranceById(@PathVariable Integer id)
    {
        Optional<InsuranceModel> insuranceModel  = insuranceRepo.findInsuranceById(id);
        try {
            if(insuranceModel.isPresent())
            {

                InsuranceResponseDTO insuranceResponseDTO = modelMapper.map(insuranceModel.get(),InsuranceResponseDTO.class);
                return  insuranceResponseDTO;
            }else {
                throw new RuntimeException("Enter valid Insurance Id");
            }
        }catch (Exception e)
        {
            throw  new RuntimeException(e.getMessage());
        }
    }

}
