package com.example.tutoring_center.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record ShiftResponse(Long id,
                            @JsonFormat(pattern = "HH:mm")
                            LocalTime startTime,
                            @JsonFormat(pattern = "HH:mm")
                            LocalTime endTime,
                            Integer dayOfWeek,
                            RoomResponse room
                            )
{}
