package com.example.Week1Introduction.Week_1_.Introduction.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;
    private Student student;

    @BeforeEach
    void setup()
    {
        student = Student.builder().email("1741rishabh@gmail.com")
                .name("rishbah")
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsValid_thenReturnStudent() {
        //Arrange, given
        studentRepo.save(student);
        //Act when
        Optional<Student> student1 = studentRepo.findByEmail("1741rishabh@gmail.com");
        //Assert Then
        assertThat(student1.get()).isNotNull();
        assertThat(student1.get().getEmail()).isEqualTo("1741rishabh@gmail.com");
    }
    @Test
    void testFindByEmail_whenEmailIsWrong_thenReturnEmptyStudentObject() {
//        Give
        String email = "nayra@gmail.com";
//        When
        Optional<Student> student1 = studentRepo.findByEmail(email);
//        Then
        assertThat(student1.isPresent()).isEqualTo(false);

    }
}