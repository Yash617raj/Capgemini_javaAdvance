package com.cap.restaurentManagment;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.cap.entity.MenuItem;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MenuItem.class)
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== RESTAURANT CRUD MENU =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();

                    sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Available (true/false): ");
                    boolean available = sc.nextBoolean();

                    createMenuItem(factory, name, price, category, available);
                    break;

                case 2:
                    viewAllItems(factory);
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    updatePrice(factory, updateId, sc);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteItem(factory, deleteId);
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

    private static void createMenuItem(SessionFactory factory,String name,double price,String category,boolean available) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            MenuItem item = new MenuItem(name, price, category, available);
            session.persist(item);
            tx.commit();
            System.out.println("Menu item created successfully.");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void viewAllItems(SessionFactory factory) {
        Session session = factory.openSession();

        try {
            Query<MenuItem> query =session.createQuery("FROM MenuItem", MenuItem.class);
            List<MenuItem> list = query.list();

            System.out.println("\nID | Name | Price | Category | Available");

            for (MenuItem item : list) {
                System.out.println(item.getId() + ", " +item.getName() + ", " +item.getPrice() + ", " +item.getCategory() + ", " +item.isAvailable()
                );
            }
        } finally {
            session.close();
        }
    }

    private static void updatePrice(SessionFactory factory,int id,Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            MenuItem item = session.get(MenuItem.class, id);

            if (item != null) {
                System.out.print("Enter new price: ");
                double newPrice = sc.nextDouble();
                item.setPrice(newPrice);
                tx.commit();
                System.out.println("Price updated successfully.");

            } else {
                System.out.println("Menu item not found.");
                tx.rollback();
            }

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    private static void deleteItem(SessionFactory factory, int id) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            MenuItem item = session.get(MenuItem.class, id);

            if (item != null) {
                session.remove(item);
                tx.commit();
                System.out.println("Menu item deleted successfully.");
            } else {
                System.out.println("Menu item not found.");
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
