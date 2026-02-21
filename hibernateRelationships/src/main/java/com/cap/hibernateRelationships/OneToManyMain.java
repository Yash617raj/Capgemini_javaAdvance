package com.cap.hibernateRelationships;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

import com.cap.entity.Order;
import com.cap.entity.Customer;


public class OneToManyMain {
	public static void main(String[] args) {
		System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");
		
		Scanner sc = new Scanner(System.in);
		
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Order.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		
        while (true) {

            System.out.println("\n---- MENU ----");
            System.out.println("1. Add Customer with Orders");
            System.out.println("2. View Customer (Customer → Orders)");
            System.out.println("3. View Order (Order → Customer)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                System.out.println("Enter Customer Name:");
                String name = sc.nextLine();

                Customer customer = new Customer(name);

                System.out.println("How many orders?");
                int count = sc.nextInt();
                sc.nextLine();

                List<Order> orders = new ArrayList<>();

                for (int i = 1; i <= count; i++) {
                    System.out.println("Enter Order Date for Order " + i + ":");
                    String date = sc.nextLine();

                    Order order = new Order();
                    order.setOrderDate(date);
                    order.setCustomer(customer);

                    orders.add(order);
                }

                customer.setOrders(orders);

                session.persist(customer);

                tx.commit();
                session.close();

                System.out.println("Customer & Orders saved successfully!");
                break;

            case 2:
                Session session2 = factory.openSession();

                System.out.println("Enter Customer ID:");
                int custId = sc.nextInt();

                Customer c = session2.get(Customer.class, custId);

                if (c != null) {
                    System.out.println("Customer Name: " + c.getName());

                    if (c.getOrders() != null) {
                        System.out.println("Orders:");
                        for (Order o : c.getOrders()) {
                            System.out.println("Order ID: " + o.getId()
                                    + ", Date: " + o.getOrderDate());
                        }
                    } else {
                        System.out.println("No Orders Found.");
                    }
                } else {
                    System.out.println("Customer not found!");
                }

                session2.close();
                break;

            case 3:
                Session session3 = factory.openSession();

                System.out.println("Enter Order ID:");
                int orderId = sc.nextInt();

                Order ord = session3.get(Order.class, orderId);

                if (ord != null) {
                    System.out.println("Order Date: " + ord.getOrderDate());

                    if (ord.getCustomer() != null) {
                        System.out.println("Belongs To Customer: "
                                + ord.getCustomer().getName());
                    }
                } else {
                    System.out.println("Order not found!");
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
