package multiNotification;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class RateLimiter {
	
	@PostConstruct
	public void init() {
		System.out.println("Initializing Rate limits");
	}
	
	public void rate(String mes) {
		System.out.println("Rate = "+mes);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Cleaning up rate Cache");
	}
}
