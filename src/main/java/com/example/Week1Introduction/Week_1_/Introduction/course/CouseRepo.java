package com.example.Week1Introduction.Week_1_.Introduction.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouseRepo extends JpaRepository<Course,Integer> {
}
