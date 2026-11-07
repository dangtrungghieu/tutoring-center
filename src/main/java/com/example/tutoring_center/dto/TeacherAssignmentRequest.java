package com.example.tutoring_center.dto;

import jakarta.validation.constraints.NotNull;

public record TeacherAssignmentRequest(@NotNull(message = "Không tìm thấy giáo viên này!")
                                       Long teacherId) {
}
