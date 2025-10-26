package com.example.Week1Introduction.Week_1_.Introduction.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseOutputDTO> getCourse()
    {
        return courseService.getCourse();
    }

    @PostMapping
    public CourseOutputDTO postCourse(@RequestBody CourseInputDTO courseInputDTO)
    {
        return courseService.postCourse(courseInputDTO);
    }

    @GetMapping("/{id}")
    public CourseOutputDTO getbyId(@PathVariable Integer id)
    {
        return courseService.getCourseById(id);
    }
    @DeleteMapping("/{id}")
    public void delateBYiId(@PathVariable Integer id){
        courseService.delateById(id);
    }

}
