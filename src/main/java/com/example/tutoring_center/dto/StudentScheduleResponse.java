package com.example.tutoring_center.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record StudentScheduleResponse(String subject,
                                      Integer grade,
                                      Integer dayOfWeek,
                                      @JsonFormat(pattern = "HH:mm")
                                      LocalTime startTime,
                                      @JsonFormat(pattern = "HH:mm")
                                      LocalTime endTime,
                                      String roomAddress
                                      ) {
}
