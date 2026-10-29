package com.example.tutoring_center.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestUpdate(
        @NotBlank(message = "Mật khẩu không được để trống!")
        @Size(min = 8, message = "Độ dài tối thiểu là 8 ký tự!")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@#$!%*?&._-]+$",
                message = "Mật khẩu phải có chữ hoa, chữ thường, số!"
        )
        String password,
        String role) {
}
