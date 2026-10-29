package com.example.tutoring_center.dto;

import java.time.LocalDateTime;
import java.util.Map;
import static com.example.tutoring_center.DateUtils.FORMATTER;
public record ErrorResponse(String timestamp, int statusCode, String message, Map<String, String> errors) {
    public ErrorResponse(int statusCode, String message){
        this(FORMATTER.format(LocalDateTime.now()), statusCode, message, null);
    }
    public ErrorResponse(int statusCode, String message, Map<String, String> errors){
        this(FORMATTER.format(LocalDateTime.now()), statusCode, message, errors);
    }
}
