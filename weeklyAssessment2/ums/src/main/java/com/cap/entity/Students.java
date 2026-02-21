package com.cap.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id")
    private Student_Id_Cards idCard;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Courses> courses = new ArrayList<>();

    public Students() {
    }

    public Students(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Student_Id_Cards getIdCard() {
        return idCard;
    }

    public void setIdCard(Student_Id_Cards idCard) {
        this.idCard = idCard;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public void addCourse(Courses course) {
        this.courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Courses course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
    }
}