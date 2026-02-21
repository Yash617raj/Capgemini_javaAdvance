package com.cap.ums;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Courses;
import com.cap.entity.Departments;
import com.cap.entity.Student_Id_Cards;
import com.cap.entity.Students;

import java.util.*;

public class App {

    public static void main(String[] args) {

        System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");

        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Students.class)
                .addAnnotatedClass(Courses.class)
                .addAnnotatedClass(Departments.class)
                .addAnnotatedClass(Student_Id_Cards.class);

        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {

            System.out.println("\n---- MENU ----");
            System.out.println("1. Add Department");
            System.out.println("2. Add Student (with Dept, IDCard & Courses)");
            System.out.println("3. View Student Details");
            System.out.println("4. View Department Students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    Session session1 = factory.openSession();
                    Transaction tx1 = session1.beginTransaction();

                    System.out.print("Enter Department Name: ");
                    String deptName = sc.nextLine();

                    Departments dept = new Departments();
                    dept.setName(deptName);

                    session1.persist(dept);

                    tx1.commit();
                    session1.close();

                    System.out.println("Department Saved Successfully!");
                    break;

                case 2:

                    Session session2 = factory.openSession();
                    Transaction tx2 = session2.beginTransaction();

                    System.out.print("Enter Student Name: ");
                    String studentName = sc.nextLine();

                    Students student = new Students();
                    student.setName(studentName);

                    System.out.print("Enter Department ID: ");
                    int deptId = sc.nextInt();
                    sc.nextLine();

                    Departments existingDept =
                            session2.get(Departments.class, deptId);

                    if (existingDept == null) {
                        System.out.println("Department not found!");
                        session2.close();
                        break;
                    }

                    student.setDepartment(existingDept);

                    System.out.print("Enter ID Card Number: ");
                    String cardNumber = sc.nextLine();

                    Student_Id_Cards idCard = new Student_Id_Cards();
                    idCard.setCardNumber(cardNumber);

                    student.setIdCard(idCard);
                    idCard.setStudent(student);

                    System.out.print("How many courses? ");
                    int count = sc.nextInt();
                    sc.nextLine();

                    for (int i = 1; i <= count; i++) {

                        System.out.print("Enter Course Name " + i + ": ");
                        String courseName = sc.nextLine();

                        Courses course = session2
                                .createQuery("from Courses where courseName = :name", Courses.class)
                                .setParameter("name", courseName)
                                .uniqueResult();

                        if (course == null) {
                            course = new Courses();
                            course.setCourseName(courseName);
                            session2.persist(course);
                        }

                        student.addCourse(course);
                    }

                    session2.persist(student);

                    tx2.commit();
                    session2.close();

                    System.out.println("Student Saved Successfully!");
                    break;

                case 3:

                    Session session3 = factory.openSession();

                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    Students s = session3.get(Students.class, studentId);

                    if (s != null) {

                        System.out.println("\nStudent Name: " + s.getName());

                        if (s.getDepartment() != null) {
                            System.out.println("Department: "
                                    + s.getDepartment().getName());
                        }

                        if (s.getIdCard() != null) {
                            System.out.println("ID Card: "
                                    + s.getIdCard().getCardNumber());
                        }

                        if (s.getCourses() != null) {
                            System.out.println("Courses:");
                            for (Courses c : s.getCourses()) {
                                System.out.println("Course ID: "
                                        + c.getId()
                                        + ", Name: "
                                        + c.getCourseName());
                            }
                        }

                    } else {
                        System.out.println("Student not found!");
                    }

                    session3.close();
                    break;

                case 4:

                    Session session4 = factory.openSession();

                    System.out.print("Enter Department ID: ");
                    int departmentId = sc.nextInt();
                    sc.nextLine();

                    Departments d =
                            session4.get(Departments.class, departmentId);

                    if (d != null) {

                        System.out.println("\nDepartment: " + d.getName());

                        if (d.getStudents() != null) {
                            System.out.println("Students:");
                            for (Students st : d.getStudents()) {
                                System.out.println("Student ID: "
                                        + st.getId()
                                        + ", Name: "
                                        + st.getName());
                            }
                        }

                    } else {
                        System.out.println("Department not found!");
                    }

                    session4.close();
                    break;

                case 5:
                    factory.close();
                    sc.close();
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}