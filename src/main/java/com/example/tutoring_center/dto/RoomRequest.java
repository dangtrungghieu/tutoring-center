package com.example.tutoring_center.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomRequest(
        @NotBlank(message = "Địa chỉ không được để trống!")
        String address,
        @NotNull(message = "Sức chứa không được để trống!")
        @Min(value = 1, message = "Sức chứa phải lớn hơn 0!")
        Integer capacity
)
{}
