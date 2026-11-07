package com.example.tutoring_center.service;

import com.example.tutoring_center.dto.*;

import java.util.List;

public interface ClassService {
    ClassResponse createClass(ClassRequest request);
    List<ClassResponse> findAllClassesForStudent();
    List<ClassResponse> findAllClassesForAdmin();
    ClassResponse findClassById(Long id);
    List<ClassResponse> findClassByRoomAddress(String roomAddress);
    List<ClassResponse> findClassByShiftDayOfWeek(Integer shiftDayOfWeek);
    ClassResponse updateClass(Long id, ClassRequest request);
    void deleteClass(Long id);
    EnrollmentResponse createEnrollment(Long classId,EnrollmentRequest enrollmentRequest);
    TeacherAssignmentResponse createTeacherAssignment(Long classId, TeacherAssignmentRequest teacherAssignmentRequest);
}
