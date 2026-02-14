package com.cap.hibernateDemo;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.hibernateDemo.entity.Student;

public class App {

    public static void main(String[] args) {
    	System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Create Student");
            System.out.println("2. Read Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                	sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter marks: ");
                    int marks = sc.nextInt();
                    createStudent(factory, name, marks);
                    break;

                case 2:
                    System.out.print("Enter ID to read: ");
                    int readId = sc.nextInt();
                    readStudent(factory, readId);
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    updateStudent(factory, updateId, sc);
                    break;


                case 4:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteStudent(factory, deleteId);
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    sc.close();
                    factory.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void createStudent(SessionFactory factory, String name, int marks) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student st = new Student(name, marks);
            session.persist(st);
            tx.commit();
            System.out.println("Student created successfully.");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void readStudent(SessionFactory factory, int id) {

    	  Session session = factory.openSession();

          try {
              Student student = session.get(Student.class, id);

              if (student != null) {
                  System.out.println("ID: " + student.getId());
                  System.out.println("Name: " + student.getName());
                  System.out.println("Marks: " + student.getMarks());
              } else {
                  System.out.println("Student not found.");
              }

          } finally {
              session.close();
          }
    }

    private static void updateStudent(SessionFactory factory, int id, Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student student = session.get(Student.class, id);

            if (student != null) {

                System.out.println("What do you want to update?");
                System.out.println("1. Name");
                System.out.println("2. Marks");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {

                    case 1:
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        student.setName(newName);
                        break;

                    case 2:
                        System.out.print("Enter new marks: ");
                        int newMarks = sc.nextInt();
                        student.setMarks(newMarks);
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        tx.rollback();
                        session.close();
                        return;
                }

                tx.commit(); 
                System.out.println("Student updated successfully.");

            } else {
                System.out.println("Student not found.");
                tx.rollback();
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    private static void deleteStudent(SessionFactory factory, int id) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student student = session.get(Student.class, id);

            if (student != null) {
                session.remove(student);
                tx.commit();
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
                tx.rollback();
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
