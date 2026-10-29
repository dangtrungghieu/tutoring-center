package com.example.tutoring_center.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TeacherRequest(
        @NotBlank(message = "Họ tên không được để trống!")
        String fullName,
        @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải bao gồm đúng 10 chữ số!")
        String phone,
        Long userId
){}
