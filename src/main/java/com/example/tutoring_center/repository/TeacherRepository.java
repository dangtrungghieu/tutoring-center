package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByUserId(Long userId);
    List<Teacher> findAllByIsDeletedFalse();
    Optional<Teacher> findByIdAndIsDeletedFalse(Long id);
    boolean existsByUser_IdAndIdNot(Long userId, Long teacherId);
}
