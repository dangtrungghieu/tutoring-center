package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndStudentClassId(Long studentId, Long classId);
}
