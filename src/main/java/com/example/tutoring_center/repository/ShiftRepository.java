package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
