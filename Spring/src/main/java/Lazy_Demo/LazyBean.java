package Lazy_Demo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LazyBean {
	public LazyBean(){
		System.out.println("Lazy bean is created!");
	}
	
	public void start() {
		System.out.println("Lazy Bean has started");
	}
}
