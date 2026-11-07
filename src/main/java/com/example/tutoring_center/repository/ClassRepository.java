package com.example.tutoring_center.repository;

import com.example.tutoring_center.dto.ClassResponse;
import com.example.tutoring_center.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findAllClassesByIsDeletedFalse();
    List<Class> findClassesByShift_Room_Address(String roomAddress);
    List<Class> findClassesByShiftDayOfWeek(Integer shiftDayOfWeek);
    boolean existsByShiftId_IdAndIdNot(Long shiftId ,Long classId);
}
