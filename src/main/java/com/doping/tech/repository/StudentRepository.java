package com.doping.tech.repository;

import com.doping.tech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndStatus(Long id, boolean status);
}
