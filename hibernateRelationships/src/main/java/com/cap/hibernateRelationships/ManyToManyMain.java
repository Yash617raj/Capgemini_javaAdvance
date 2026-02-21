package com.cap.hibernateRelationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Course;
import com.cap.entity.Student;

public class ManyToManyMain {

    public static void main(String[] args) {

        System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");

        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class);

        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {

        	  System.out.println("---- MENU ----");
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
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                System.out.println("Enter Student Name:");
                String studentName = sc.nextLine();

                Student student = new Student(studentName);

                System.out.println("How many courses?");
                int count = sc.nextInt();
                sc.nextLine();

                List<Course> courses = new ArrayList<>();

                for (int i = 1; i <= count; i++) {
                    System.out.println("Enter Course Name " + i + ":");
                    String courseName = sc.nextLine();

                    Course course = session
                            .createQuery("from Course where courseName = :name", Course.class)
                            .setParameter("name", courseName)
                            .uniqueResult();

                    if (course == null) {
                        course = new Course(courseName);
                        session.persist(course);
                    }

                    courses.add(course);
                }

                student.setCourses(courses);

                session.persist(student);

                tx.commit();
                session.close();

                System.out.println("Student & Courses saved successfully!");
                break;

            case 2:
                Session session2 = factory.openSession();

                System.out.println("Enter Student ID:");
                int studentId = sc.nextInt();

                Student s = session2.get(Student.class, studentId);

                if (s != null) {
                    System.out.println("Student Name: " + s.getStudentName());

                    if (s.getCourses() != null) {
                        System.out.println("Courses:");
                        for (Course c : s.getCourses()) {
                            System.out.println("Course ID: " + c.getId()
                                    + ", Name: " + c.getCourseName());
                        }
                    }
                } else {
                    System.out.println("Student not found!");
                }

                session2.close();
                break;

            case 3:
                Session session3 = factory.openSession();

                System.out.println("Enter Course ID:");
                int courseId = sc.nextInt();

                Course c = session3.get(Course.class, courseId);

                if (c != null) {
                    System.out.println("Course Name: " + c.getCourseName());

                    if (c.getStudents() != null) {
                        System.out.println("Students:");
                        for (Student st : c.getStudents()) {
                            System.out.println("Student ID: " + st.getId()
                                    + ", Name: " + st.getStudentName());
                        }
                    }
                } else {
                    System.out.println("Course not found!");
                }

                session3.close();
                break;

            case 4:
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
