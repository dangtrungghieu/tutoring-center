package com.example.tutoring_center.controller;

import com.example.tutoring_center.dto.TeacherRequest;
import com.example.tutoring_center.dto.TeacherRequestUpdate;
import com.example.tutoring_center.dto.TeacherResponse;
import com.example.tutoring_center.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/teachers" , name = "TEACHER_CONTROLLER_GET_METHOD")
public class TeacherController {
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@Valid @RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherRequest));
    }
    @GetMapping("/public")
    public ResponseEntity<List<TeacherResponse>> getAllTeachersToStudent() {
        return ResponseEntity.ok(teacherService.findAllTeachersToStudent());
    }
    @GetMapping("/admin")
    public ResponseEntity<List<TeacherResponse>> getAllTeachersToAdmin(){
        return ResponseEntity.ok(teacherService.findAllTeachersToAdmin());
    }
    @GetMapping("/public/{id}")
    public ResponseEntity<TeacherResponse> getTeacherByIdToStudent(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.findTeacherByIdToStudent(id));
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<TeacherResponse> getTeacherByIdToAdmin(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.findTeacherByIdToAdmin(id));
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteTeacherById(@PathVariable Long id){
        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<TeacherResponse> updateTeacherByAdmin(@PathVariable Long id, @Valid @RequestBody TeacherRequest teacherRequest){
        return ResponseEntity.ok(teacherService.updateTeacherByAdmin(id, teacherRequest));
    }
    @PutMapping("/me/{id}")
    public ResponseEntity<TeacherResponse> updateTeacherByTeacher(@PathVariable Long id, @Valid @RequestBody TeacherRequestUpdate teacherRequestUpdate){
        return ResponseEntity.ok(teacherService.updateTeacherByIdToTeacher(id, teacherRequestUpdate));
    }
}
