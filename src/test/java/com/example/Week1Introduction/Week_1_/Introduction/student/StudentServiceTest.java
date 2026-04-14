package com.example.Week1Introduction.Week_1_.Introduction.student;

import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private  StudentRepo studentRepo;
    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private StudentService studentService;
    Integer id=1;
    Optional<Student> student;


    @BeforeEach
    void setup()
    {
         id=1;
         student = Optional.of(Student
                .builder().name("Rishabh").email("rishabh1741@gmail.com").id(1).build());
    }


    @Test
    void TestGetStudentById()
    {
        // Assign
        when(studentRepo.findByIdStudent(id)).thenReturn(student);
        //act
        StudentOutputDTO studentOutputDTO = studentService.getStudentById(id);
        // assert
        assertThat(studentOutputDTO.getId()).isEqualTo(id);
        assertThat(studentOutputDTO.getEmail()).isEqualTo(student.get().getEmail());
    }
    @Test
    void TestGetStudentById_when_Student_is_not_present()
    {
        // Assign
        when(studentRepo.findByIdStudent(id)).thenReturn(Optional.empty());
        // act + asset
        assertThatThrownBy(()-> studentService.getStudentById(id))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Enter a Valid Student Id");


    }



}