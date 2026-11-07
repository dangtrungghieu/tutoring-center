package com.example.tutoring_center.dto;

import com.example.tutoring_center.entity.Shift;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record ClassResponse(Long id,
                            String subject,
                            Integer grade,
                            Integer dayOfWeek,
                            @JsonFormat(pattern = "HH:mm")
                            LocalTime startTime,
                            @JsonFormat(pattern = "HH:mm")
                            LocalTime endTime,
                            String addressRoom
                            )
{}
