package com.example.Week1Introduction.Week_1_.Introduction.admissionRecord;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admission")
@RequiredArgsConstructor
public class AdmissionController {
    private final AdmissionService admissionService;

    @PostMapping
    public AdmissionOutputDTO addAdmission(@RequestBody @Valid AdmissionInputDTO admissionInputDTO)
    {
        return admissionService.addAdmission(admissionInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteByid(@PathVariable Integer id)
    {
        admissionService.deleteByid(id);
    }

    @GetMapping
    public List<AdmissionOutputDTO> getAllAdmission()
    {
        return admissionService.getAllAdmisiion();
    }

    @GetMapping("/{id}")
    public AdmissionOutputDTO getAdmissionById(@PathVariable Integer id)
    {
        return admissionService.getAdmissionById(id);
    }

}
