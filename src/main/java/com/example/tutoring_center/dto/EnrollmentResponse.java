package com.example.tutoring_center.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record EnrollmentResponse(String studentFullName,
                                 String classSubject,
                                 Integer grade,
                                 Integer dayOfWeek,
                                 @JsonFormat(pattern = "HH:mm")
                                 LocalTime startTime,
                                 @JsonFormat(pattern = "HH:mm")
                                 LocalTime endTime,
                                 String addressRoom,
                                 String createdAt
                                 ) {
}
