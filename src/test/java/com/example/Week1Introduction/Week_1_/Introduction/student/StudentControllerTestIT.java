package com.example.Week1Introduction.Week_1_.Introduction.student;

import com.example.Week1Introduction.Week_1_.Introduction.api.model.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
class StudentControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;

    private Student student;
    private StudentOutputDTO studentDto;

    @BeforeEach
    void setUp()
    {
        student = Student.builder()
                .email("rishabh1741@gmail.com")
                .name("rishabh")
                .build();
        studentDto = modelMapper.map(student,StudentOutputDTO.class);
        System.out.println(studentDto.getEmail() + "DTO EMAIL ");
    }

    @Test
    void testGetStudentById_success()
    {
        Student savedStudent=  studentRepo.save(student);
        webTestClient.get()
                .uri("/student/{id}",savedStudent.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.data.name").isEqualTo(savedStudent.getName());

    }
}