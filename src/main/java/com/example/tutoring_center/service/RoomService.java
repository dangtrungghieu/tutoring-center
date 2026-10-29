package com.example.tutoring_center.service;

import com.example.tutoring_center.dto.RoomRequest;
import com.example.tutoring_center.dto.RoomResponse;
import com.example.tutoring_center.entity.Room;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomRequest roomRequest);
    List<RoomResponse> findAllRooms();
    RoomResponse findRoomById(Long id);
    RoomResponse updateRoom(Long id, RoomRequest roomRequest);
    void deleteRoomById(Long id);
}
