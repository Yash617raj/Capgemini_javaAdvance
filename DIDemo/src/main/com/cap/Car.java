package main.com.cap;

public class Car {
	public Engine engine;

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public Car() {
		super();
	}

	public Car(Engine engine) {
		super();
		this.engine = engine;
	}
	
}
