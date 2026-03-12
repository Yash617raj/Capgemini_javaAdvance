package com.cap.student_management_system.dto;

import lombok.Data;

@Data
public class StudentDto {
    private String name,email,course;
    private double marks;
}
