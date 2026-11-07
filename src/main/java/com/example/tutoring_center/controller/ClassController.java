package com.example.tutoring_center.controller;

import com.example.tutoring_center.dto.ClassRequest;
import com.example.tutoring_center.dto.ClassResponse;
import com.example.tutoring_center.service.ClassService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/classes", name = "CLASS_CONTROLLER_GET_METHOD")
public class ClassController {
    private final ClassService classService;
    public ClassController(ClassService classService) {
        this.classService = classService;
    }
    @PostMapping
    public ResponseEntity<ClassResponse> createClass(@Valid @RequestBody ClassRequest classRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.createClass(classRequest));
    }
    @GetMapping("/admin")
    public ResponseEntity<List<ClassResponse>> getAllClassesForAdmin() {
        return ResponseEntity.ok(classService.findAllClassesForAdmin());
    }
    @GetMapping("/student")
    public ResponseEntity<List<ClassResponse>> getAllClassesForStudent() {
        return ResponseEntity.ok(classService.findAllClassesForStudent());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClassResponse> getClassById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(classService.findClassById(id));
    }
    @GetMapping
    public ResponseEntity<List<ClassResponse>> findClassByRoomAddress(
            @RequestParam(required = false) String roomAddress,
            @RequestParam(required = false) Integer shiftDayOfWeek) {
        if (roomAddress != null) {
            return ResponseEntity.ok(classService.findClassByRoomAddress(roomAddress));
        }
        if (shiftDayOfWeek != null) {
            return ResponseEntity.ok(classService.findClassByShiftDayOfWeek(shiftDayOfWeek));
        }
        return ResponseEntity.ok(classService.findAllClassesForAdmin());
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<ClassResponse> updateClass(@PathVariable @NotNull Long id, @RequestBody ClassRequest classRequest) {
        return ResponseEntity.ok(classService.updateClass(id, classRequest));
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ClassResponse> deleteClass(@PathVariable @NotNull Long id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
