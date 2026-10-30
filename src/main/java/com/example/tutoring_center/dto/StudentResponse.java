package com.example.tutoring_center.dto;

public record StudentResponse(Long id, String fullName, String phone, UserResponseToStudent userResponseToStudent) {
}
