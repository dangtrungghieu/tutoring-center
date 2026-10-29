package com.example.tutoring_center.service;

import com.example.tutoring_center.dto.ShiftRequest;
import com.example.tutoring_center.dto.ShiftResponse;

import java.util.List;

public interface ShiftService {
    ShiftResponse createShift(ShiftRequest shiftRequest);
    List<ShiftResponse> findAllShifts();
    ShiftResponse findShiftById(Long id);
    ShiftResponse updateShift(Long id, ShiftRequest shiftRequest);
    void deleteShift(Long id);
}
