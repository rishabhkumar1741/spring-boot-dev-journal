package com.example.Week1Introduction.Week_1_.Introduction.course;

import com.example.Week1Introduction.Week_1_.Introduction.student.StudentOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CouseRepo courseRepo;
    public List<CourseOutputDTO> getCourse() {
        return courseRepo.findAll().stream().map(x-> new CourseOutputDTO(x.getId(), x.getTitle())).toList();
    }

    public CourseOutputDTO postCourse(CourseInputDTO courseInputDTO) {
        Course course = new Course();
        course.setTitle(courseInputDTO.getTitle());
        Course savecourse = courseRepo.save(course);
        return  new CourseOutputDTO(savecourse.getId(), savecourse.getTitle());
    }

    public CourseOutputDTO getCourseById(Integer courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if(course.isPresent())
        {
            return new CourseOutputDTO(course.get().getId(),course.get().getTitle());
        }else {
            throw new NoSuchElementException("Invalid course Id");
        }
    }

    public void delateById(Integer id) {
         courseRepo.deleteById(id);
    }
}
