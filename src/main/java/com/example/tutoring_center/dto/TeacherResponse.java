package com.example.tutoring_center.dto;

import com.example.tutoring_center.entity.User;

public record TeacherResponse(Long id, String fullName, String phone, User user) {
}
