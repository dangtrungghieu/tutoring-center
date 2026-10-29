package com.example.tutoring_center.service.impl;

import com.example.tutoring_center.dto.RoomRequest;
import com.example.tutoring_center.dto.RoomResponse;
import com.example.tutoring_center.entity.Room;
import com.example.tutoring_center.repository.RoomRepository;
import com.example.tutoring_center.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    private RoomResponse mapToRoomResponse(Room room) {
        return new RoomResponse(room.getId(), room.getAddress(), room.getCapacity());
    }
    @Override
    public RoomResponse createRoom(RoomRequest roomRequest){
        Room room = new Room();
        room.setAddress(roomRequest.address());
        room.setCapacity(roomRequest.capacity());
        return mapToRoomResponse(roomRepository.save(room));
    }
    @Override
    public List<RoomResponse> findAllRooms(){
        return roomRepository.findAll().stream().map(this::mapToRoomResponse).toList();
    }
    @Override
    public RoomResponse findRoomById(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID là: " + id));
        return mapToRoomResponse(room);
    }
    @Transactional
    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest){
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID là: " + id));
        room.setAddress(roomRequest.address());
        room.setCapacity(roomRequest.capacity());
        return mapToRoomResponse(roomRepository.save(room));
    }
    @Transactional
    @Override
    public void deleteRoomById(Long id){
        if(!roomRepository.existsById(id)){
            throw new RuntimeException("Không tìm thấy phòng có ID là: " + id);
        }
        roomRepository.deleteById(id);
    }
}