package com.example.Week1Introduction.Week_1_.Introduction.student;

import com.example.Week1Introduction.Week_1_.Introduction.course.Course;
import com.example.Week1Introduction.Week_1_.Introduction.course.CouseRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final CouseRepo couseRepo;
    private final ModelMapper modelMapper;

    public  List<StudentOutputDTO> getAllStudent() {
        List<Student> studentList = studentRepo.findAllStudent();
        return studentList.stream().map(data-> new StudentOutputDTO(data)).toList();

    }

    @Transactional
    public StudentOutputDTO addStudent(@Valid StudentInputDTO studentInputDTO) {
        Student student = new Student();
        student.setName(studentInputDTO.getName());
        Student data = studentRepo.save(student);
        return new StudentOutputDTO(data);
    }

    public StudentOutputDTO getStudentById(Integer id) {
        Optional<Student> student = studentRepo.findByIdStudent(id);
        if(student.isPresent())
        {
            return new StudentOutputDTO(student.get());
        }else {
            throw new NoSuchElementException("Enter a Valid Student Id");
        }

    }



    @Transactional
    public Boolean deleteStudentId(Integer id) {
        Optional<Student> student = studentRepo.findByIdStudent(id);
        if(student.isPresent())
        {
            studentRepo.deleteById(id);
            return true;
        }else {
            throw new NoSuchElementException("InValid UserId");
        }
    }

    public Student addCourse(Integer studentId, Integer courseId) {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Course> course = couseRepo.findById(courseId);
        if(student.isPresent() && course.isPresent())
        {
            student.get().getCourse().add(course.get());
            System.out.println(student.get().getCourse());
            return studentRepo.save(student.get());

        }else {
            throw new NoSuchElementException("enter a valid Student Id and Course Id");
        }

    }
}
