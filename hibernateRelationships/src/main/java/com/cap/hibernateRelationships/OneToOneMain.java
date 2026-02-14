package com.cap.hibernateRelationships;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Person;
import com.cap.entity.Passport;

public class OneToOneMain {

    public static void main(String[] args) {
    	System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");

        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {

        	System.out.println("\n---- MENU ----");
        	System.out.println("1. Add Person & Passport");
        	System.out.println("2. View Person (Person → Passport)");
        	System.out.println("3. Check Bidirectional (Passport → Person)");
        	System.out.println("4. Exit");
        	System.out.print("Enter choice: ");


            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                System.out.println("Enter Person Name:");
                String name = sc.nextLine();

                System.out.println("Enter Passport Number:");
                String passportNumber = sc.nextLine();

                System.out.println("Enter Country:");
                String country = sc.nextLine();

                Passport passport = new Passport(passportNumber, country);
                Person person = new Person();
                person.setName(name);
                person.setPassport(passport);   // sets both sides

                session.persist(person);

                tx.commit();
                session.close();

                System.out.println("Person & Passport saved successfully!");
                break;

            case 2:
                Session session2 = factory.openSession();

                System.out.println("Enter Person ID:");
                int id = sc.nextInt();

                Person p = session2.get(Person.class, id);

                if (p != null) {
                    System.out.println("Name: " + p.getName());
                    System.out.println("Passport Number: " +
                            p.getPassport().getPassportNumber());
                    System.out.println("Country: " +
                            p.getPassport().getCountry());
                } else {
                    System.out.println("Person not found!");
                }

                session2.close();
                break;

            case 3:
                Session session3 = factory.openSession();

                System.out.println("Enter Passport ID:");
                int passportId = sc.nextInt();

                Passport pass = session3.get(Passport.class, passportId);

                if (pass != null) {
                    if (pass.getPerson() != null) {
                        System.out.println("Passport Number: " + pass.getPassportNumber());
                        System.out.println("Belongs To Person: " +
                                pass.getPerson().getName());
                    } else {
                        System.out.println("Passport exists but no associated Person found.");
                    }
                } else {
                    System.out.println("Passport not found!");
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
