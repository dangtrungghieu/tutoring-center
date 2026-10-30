package com.example.tutoring_center.service;

import com.example.tutoring_center.dto.TeacherRequest;
import com.example.tutoring_center.dto.TeacherRequestUpdate;
import com.example.tutoring_center.dto.TeacherResponse;
import com.example.tutoring_center.entity.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherResponse createTeacher(TeacherRequest teacherRequest);
    List<TeacherResponse> findAllTeachersToAdmin();
    TeacherResponse findTeacherByIdToAdmin(Long id);
    TeacherResponse updateTeacherByIdToTeacher(Long id, TeacherRequestUpdate teacherRequestUpdate);
    void deleteTeacherById(Long id);
    TeacherResponse updateTeacherByAdmin(Long id, TeacherRequest teacherRequest);
    List<TeacherResponse> findAllTeachersToStudent();
    TeacherResponse findTeacherByIdToStudent(Long id);
}
