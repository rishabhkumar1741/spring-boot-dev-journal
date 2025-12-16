package com.example.Week1Introduction.Week_1_.Introduction.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends  JpaRepository<QC_EGMS_USERS,Long> {
    Optional<QC_EGMS_USERS> findByUsername(String user);
}
