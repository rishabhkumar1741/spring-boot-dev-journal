package com.example.Week1Introduction.Week_1_.Introduction.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {


    @Query("SELECT  s FROM Student s LEFT JOIN FETCH s.admissionRecord")
    List<Student> findAllStudent();

    @Query("SELECT s from Student s LEFT JOIN FETCH s.admissionRecord WHERE s.id= :id")
    Optional<Student> findByIdStudent(@Param("id") Integer id);
}
