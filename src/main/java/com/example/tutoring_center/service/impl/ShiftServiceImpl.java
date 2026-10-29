package com.example.tutoring_center.service.impl;

import com.example.tutoring_center.dto.RoomResponse;
import com.example.tutoring_center.dto.ShiftRequest;
import com.example.tutoring_center.dto.ShiftResponse;
import com.example.tutoring_center.entity.Room;
import com.example.tutoring_center.entity.Shift;
import com.example.tutoring_center.repository.RoomRepository;
import com.example.tutoring_center.repository.ShiftRepository;
import com.example.tutoring_center.service.ShiftService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final RoomRepository roomRepository;
    public ShiftServiceImpl(ShiftRepository shiftRepository,  RoomRepository roomRepository) {
        this.shiftRepository = shiftRepository;
        this.roomRepository = roomRepository;
    }
    private ShiftResponse mapToShiftResponse(Shift shift) {
        RoomResponse roomResponse;
        if (shift.getRoom() != null) {
            roomResponse = new RoomResponse(shift.getRoom().getId(), shift.getRoom().getAddress(), shift.getRoom().getCapacity());
        }else {
            roomResponse = null;
        }
        return new ShiftResponse(shift.getId(), shift.getStartTime(), shift.getEndTime(), shift.getDayOfWeek(),roomResponse);
    }
    @Override
    public ShiftResponse createShift(ShiftRequest shiftRequest){
        if (!shiftRequest.startTime().isBefore(shiftRequest.endTime())){
            throw new RuntimeException("Thời gian không hợp lệ!");
        }
        Shift shift = new Shift();
        shift.setStartTime(shiftRequest.startTime());
        shift.setEndTime(shiftRequest.endTime());
        shift.setDayOfWeek(shiftRequest.dayOfWeek());
        if(shiftRequest.roomId() != null){
            shift.setRoom(roomRepository.findById(shiftRequest.roomId()).orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID là: " + shiftRequest.roomId())));
        }else {
            shift.setRoom(null);
        }
        return mapToShiftResponse(shiftRepository.save(shift));
    }
    @Override
    public List<ShiftResponse> findAllShifts(){
        return shiftRepository.findAll().stream().map(this ::mapToShiftResponse).toList();
    }
    @Override
    public ShiftResponse findShiftById(Long id){
        Shift shift = shiftRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy ca học có ID là: " + id));
        return  mapToShiftResponse(shift);
    }
    @Transactional
    @Override
    public ShiftResponse updateShift(Long id, ShiftRequest shiftRequest){
        if (!shiftRequest.startTime().isBefore(shiftRequest.endTime())){
            throw new RuntimeException("Thời gian không hợp lệ!");
        }
        Shift shift = shiftRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy ca học có ID là: " + id));
        shift.setStartTime(shiftRequest.startTime());
        shift.setEndTime(shiftRequest.endTime());
        shift.setDayOfWeek(shiftRequest.dayOfWeek());
        if(shiftRequest.roomId() != null){
            shift.setRoom(roomRepository.findById(shiftRequest.roomId()).orElseThrow(() -> new RuntimeException("Không tìm thấy phòng có ID là: " + shiftRequest.roomId())));
        }else {
            shift.setRoom(null);
        }
        return mapToShiftResponse(shiftRepository.save(shift));
    }
    @Transactional
    @Override
    public void deleteShift(Long id){
        if (!shiftRepository.existsById(id)){
            throw new RuntimeException("Không tìm thấy ca học có ID là: " + id);
        }
        shiftRepository.deleteById(id);
    }
}
