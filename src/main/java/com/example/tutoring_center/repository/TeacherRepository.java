package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
