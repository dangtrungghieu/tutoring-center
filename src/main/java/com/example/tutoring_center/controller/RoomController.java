package com.example.tutoring_center.controller;

import com.example.tutoring_center.dto.RoomRequest;
import com.example.tutoring_center.dto.RoomResponse;
import com.example.tutoring_center.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/rooms", name = "ROOM_CONTROLLER_GET_METHOD")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(roomRequest));
    }
    @GetMapping
    public ResponseEntity<List<RoomResponse>> findAllRooms() {
        return ResponseEntity.ok(roomService.findAllRooms());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findRoomById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id,@Valid @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }
}
