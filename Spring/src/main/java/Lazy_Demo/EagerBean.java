package Lazy_Demo;

import org.springframework.stereotype.Component;

@Component
public class EagerBean {
	public EagerBean() {
		System.out.println("Eager Bean is Created!");
	}
	
	public void start() {
		System.out.println("Eager Bean has started");
	}
}
