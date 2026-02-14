package com.cap.hibernateRelationships;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Person;
import com.cap.entity.Passport;

public class temp {

    public static void main(String[] args) {
    	System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Create objects
        Passport passport = new Passport("IND99999", "India");

        Person person = new Person();
        person.setName("Yash");

        // IMPORTANT: sets both sides (bidirectional)
        person.setPassport(passport);

        session.persist(person);

        tx.commit();
        session.close();

        System.out.println("Data saved successfully!\n");

        // ---------------------------
        // CHECK BIDIRECTIONAL ACCESS
        // ---------------------------

        Session session2 = factory.openSession();

        System.out.println("----- From Person Side -----");

        Person fetchedPerson = session2.get(Person.class, person.getPersonId());

        System.out.println("Person Name: " + fetchedPerson.getName());
        System.out.println("Passport Number: " +
                fetchedPerson.getPassport().getPassportNumber());

        System.out.println("\n----- From Passport Side -----");

        Passport fetchedPassport = session2.get(Passport.class,
                fetchedPerson.getPassport().getPassportId());

        System.out.println("Passport Number: " + fetchedPassport.getPassportNumber());
        System.out.println("Belongs To Person: " +
                fetchedPassport.getPerson().getName());

        session2.close();
        factory.close();
    }
}
