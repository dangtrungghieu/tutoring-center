package com.example.tutoring_center.dto;

import java.time.LocalDateTime;

public record EnrollmentResponse(String studentFullName,
                                 String classSubject,
                                 Integer grade,
                                 LocalDateTime createdAt
                                 ) {
}
