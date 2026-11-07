package com.example.tutoring_center.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassRequest(@NotBlank(message = "Chưa nhập thông tin môn dạy!")
                            String subject,
                            @NotNull(message = "Chưa nhập thông tin khối dạy!")
                            @Min(1)
                            @Max(12)
                           Integer grade,
                            @NotNull(message = "Ca học không được để trống!")
                           Long shiftId) {
}
