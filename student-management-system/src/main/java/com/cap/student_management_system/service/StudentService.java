package com.cap.student_management_system.service;

import com.cap.student_management_system.dto.StudentDto;
import com.cap.student_management_system.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    Student createStudent(StudentDto dto);

    Student getStudentById(Long id);

    Page<Student> getAllStudents(Pageable pageable);

    Student updateStudent(Long id, StudentDto dto);

    void deleteStudent(Long id);

    void uploadProfileImage(Long id, MultipartFile file);

    void uploadAssignment(Long id, MultipartFile file);

    byte[] downloadProfileImage(Long id);

    byte[] downloadAssignment(Long id);
}
