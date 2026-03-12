package com.cap.student_management_system.service.impl;

import com.cap.student_management_system.dto.StudentDto;
import com.cap.student_management_system.entity.Student;
import com.cap.student_management_system.repository.StudentRepository;
import com.cap.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(StudentDto dto) {

        Student student=new Student();

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setMarks(dto.getMarks());

        return studentRepository.save(student);
    }

    @Override
    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student updateStudent(Long id, StudentDto dto) {
        Student student=getStudentById(id);

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setMarks(dto.getMarks());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void uploadProfileImage(Long id, MultipartFile file) {
        try {

            Student student=getStudentById(id);
            student.setProfileImage(file.getBytes());
            studentRepository.save(student);

        }catch(Exception e){
            throw new RuntimeException("File upload failed");
        }
    }

    @Override
    public void uploadAssignment(Long id, MultipartFile file) {
        try{
            Student student=getStudentById(id);
            student.setAssignmentFile(file.getBytes());
            studentRepository.save(student);
        }catch(Exception e){
            throw new RuntimeException("Upload failed");
        }
    }

    @Override
    public byte[] downloadProfileImage(Long id) {

        Student student=getStudentById(id);
        return student.getProfileImage();
    }

    @Override
    public byte[] downloadAssignment(Long id) {

        Student student=getStudentById(id);
        return student.getAssignmentFile();
    }
}
