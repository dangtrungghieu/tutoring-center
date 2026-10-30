package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUserId(Long userId);
    List<Student> findByFullNameContainingIgnoreCase(String name);
    List<Student> findByPhoneContainingIgnoreCase(String phone);
    List<Student> findByUserEmail(String userEmail);
    boolean existsByUser_IdAndIdNot(Long userId, Long id);
}
