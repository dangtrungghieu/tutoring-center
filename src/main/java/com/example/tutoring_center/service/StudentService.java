package com.example.tutoring_center.service;

import com.example.tutoring_center.dto.StudentRequest;
import com.example.tutoring_center.dto.StudentRequestUpdate;
import com.example.tutoring_center.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest studentRequest);
    List<StudentResponse> findAllStudents();
    StudentResponse findStudentById(Long id);
    List<StudentResponse> findAllStudentByName(String name);
    List<StudentResponse> findAllStudentByPhone(String phone);
    List<StudentResponse> findStudentByUserEmail(String userEmail);
    StudentResponse updateStudentForStudent(Long id,StudentRequestUpdate studentRequestUpdate);
    StudentResponse updateStudentForAdmin(Long id, StudentRequest studentRequest);
    //void deleteStudentByIdForTeacher(Long id);
    void deleteStudentByIdForAdmin(Long id);
}
