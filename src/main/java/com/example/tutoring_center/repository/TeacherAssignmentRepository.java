package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.TeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, Long> {
    boolean existsByTeacherIdAndClazzId(Long teacherId, Long classId);
}
