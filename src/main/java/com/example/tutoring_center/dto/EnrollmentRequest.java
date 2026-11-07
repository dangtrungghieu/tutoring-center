package com.example.tutoring_center.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(@NotNull(message = "Không tìm thấy học sinh này!")
                                Long studentId) {
}
