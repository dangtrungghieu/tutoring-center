package com.example.tutoring_center.controller;

import com.example.tutoring_center.dto.ShiftRequest;
import com.example.tutoring_center.dto.ShiftResponse;
import com.example.tutoring_center.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/shifts", name = "SHIFT_CONTROLLER_GET_METHOD")
public class ShiftController {
    private final ShiftService shiftService;
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }
    @PostMapping
    public ResponseEntity<ShiftResponse> createShift(@Valid @RequestBody ShiftRequest shiftRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftService.createShift(shiftRequest));
    }
    @GetMapping
    public ResponseEntity<List<ShiftResponse>> getAllShifts() {
        return ResponseEntity.ok(shiftService.findAllShifts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponse> getShiftById(@PathVariable Long id) {
        return ResponseEntity.ok(shiftService.findShiftById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShiftResponse> updateShift(@PathVariable Long id, @Valid @RequestBody ShiftRequest shiftRequest) {
        return ResponseEntity.ok(shiftService.updateShift(id, shiftRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShiftById(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return ResponseEntity.noContent().build();
    }
}
