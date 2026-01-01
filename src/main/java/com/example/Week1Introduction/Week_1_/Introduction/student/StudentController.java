package com.example.Week1Introduction.Week_1_.Introduction.student;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    Logger log = LoggerFactory.getLogger(StudentController.class);

    @GetMapping
    public List<StudentOutputDTO>  getAllStudent()
    {
        log.warn("Warn message");
        log.info("info message");
        log.debug("debug message");
        return studentService.getAllStudent();
    }
    @GetMapping("/email")
    public StudentOutputDTO getStudentByEmail(@RequestBody StudentInputDTO studentInputDTO)
    {
        return studentService.getStudentByEmail(studentInputDTO);
    }


    @PostMapping
    public StudentOutputDTO addStudent(@RequestBody @Valid StudentInputDTO studentInputDTO)
    {
        return studentService.addStudent(studentInputDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudentId(@PathVariable Integer id)
    {
        System.out.println("Void");
        studentService.deleteStudentId(id);
    }


    @GetMapping(path = "/{id}")
    public StudentOutputDTO  getStudentById(@PathVariable Integer id)
    {

        return studentService.getStudentById(id);
    }

    //Course
    @GetMapping(path = "/{studentId}/course/{courseId}")
    public Student addCourseById(@PathVariable Integer studentId,@PathVariable Integer courseId)
    {
        return studentService.addCourse(studentId,courseId);
    }



}
