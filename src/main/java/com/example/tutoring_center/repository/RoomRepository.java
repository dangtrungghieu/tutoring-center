package com.example.tutoring_center.repository;

import com.example.tutoring_center.entity.Room;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
