package com.cap.student_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name,email,course;
    double marks;

    @Lob
    private byte[] profileImage;

    @Lob
    private byte[] assignmentFile;
}

