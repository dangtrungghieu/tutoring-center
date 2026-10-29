package com.example.tutoring_center.dto;

import com.example.tutoring_center.entity.Room;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ShiftRequest(
        @NotNull(message = "Thời gian bắt đầu không được trống!")
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime,
        @NotNull(message = "Thời gian kết thúc không được trống!")
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime,
        @NotNull(message = "Không được để ngày học trống!") //2 = T2, 3 = T3,..., 8 = CN
        @Min(value = 2, message = "Giá trị phải từ 2 -> 8 (2 = T2 -> 8 = CN)!")
        @Max(value = 8, message = "Giá trị phải từ 2 -> 8 (2 = T2 -> 8 = CN)!")
        Integer dayOfWeek,
        Long roomId
        )
{}
