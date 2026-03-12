package com.cap.student_management_system.controller;

import com.cap.student_management_system.dto.StudentDto;
import com.cap.student_management_system.entity.Student;
import com.cap.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody StudentDto dto){

        return studentService.createStudent(dto);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){

        return studentService.getStudentById(id);
    }

    @GetMapping
    public Page<Student> getAllStudents(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="5") int size){

        return studentService.getAllStudents(PageRequest.of(page,size));
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody StudentDto dto){

        return studentService.updateStudent(id,dto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){

        studentService.deleteStudent(id);
    }

    @PostMapping("/{id}/upload-image")
    public String uploadImage(@PathVariable Long id,@RequestParam MultipartFile file){

        studentService.uploadProfileImage(id,file);

        return "Image Uploaded";
    }

    @PostMapping("/{id}/upload-assignment")
    public String uploadAssignment(@PathVariable Long id,@RequestParam MultipartFile file){

        studentService.uploadAssignment(id,file);

        return "Assignment Uploaded";
    }

    @GetMapping("/{id}/profile-image")
    public byte[] downloadImage(@PathVariable Long id){

        return studentService.downloadProfileImage(id);
    }

    @GetMapping("/{id}/assignment")
    public byte[] downloadAssignment(@PathVariable Long id){

        return studentService.downloadAssignment(id);
    }
}
