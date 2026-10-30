package com.example.tutoring_center.controller;

import com.example.tutoring_center.dto.StudentRequest;
import com.example.tutoring_center.dto.StudentRequestUpdate;
import com.example.tutoring_center.dto.StudentResponse;
import com.example.tutoring_center.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students", name = "STUDENT_CONTROLLER_GET_METHOD")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequest));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponse>>findStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email)
    {
        if (name != null) {
            return ResponseEntity.ok(studentService.findAllStudentByName(name));
        }
        if (phone != null) {
            return ResponseEntity.ok(studentService.findAllStudentByPhone(phone));
        }
        if (email != null) {
            return ResponseEntity.ok(studentService.findStudentByUserEmail(email));
        }
        return ResponseEntity.ok(studentService.findAllStudents());
    }
    @PatchMapping("/me/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable @NotNull Long id, @RequestBody StudentRequestUpdate studentRequestUpdate) {
        return ResponseEntity.ok(studentService.updateStudentForStudent(id, studentRequestUpdate));
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<StudentResponse>  updateStudentForAdmin(@PathVariable @NotNull Long id, @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.updateStudentForAdmin(id, studentRequest));
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable @NotNull Long id) {
        studentService.deleteStudentByIdForAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
