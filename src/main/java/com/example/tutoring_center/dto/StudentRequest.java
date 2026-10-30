package com.example.tutoring_center.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record StudentRequest(
        @NotBlank(message = "Họ tên học viên không được để trống!")
        @Pattern(
                regexp = "^[\\p{L}\\s]+$",
                message = "Chỉ được chứa chữ cái và khoảng trắng!"
        )
        String fullName,
        String phone,
        @NotNull (message = "Tài khoản liên kết không được để trống!")
        Long userId
) {}
