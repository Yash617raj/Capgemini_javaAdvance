package driver;

import java.util.Scanner;

import main.com.cap.Car;
import main.com.cap.DieselEngine;
import main.com.cap.Engine;
import main.com.cap.PetrolEngine;

public class Main {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("1: Petrol engine");
		System.out.println("2: Diesel engine");
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		// Eager inst.
//		Car car = new Car();
		Engine engine = null;
		switch (choice) {
		case 1:
			// Lazy inst.
			engine = new PetrolEngine();
			break;
		case 2:
			engine = new DieselEngine();
			break;
		}
		// Field injection.
//		car.engine = engine;
//
//		car.engine.run();
//		System.out.println(car.engine.getClass());
		
		// Setter injection.
//		car.setEngine(engine);
//		car.getEngine().run();
//		System.out.println(car.getEngine().getClass());
		
		Car car = new Car(engine);
		car.getEngine().run();
		System.out.println(car.getEngine().getClass());
	}

}
