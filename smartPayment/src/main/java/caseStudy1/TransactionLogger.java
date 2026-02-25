package caseStudy1;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TransactionLogger {
	
	 public TransactionLogger() {
		 System.out.println("Transaction logger is created");
	 }
	 
	@PostConstruct
	public void init() {
		System.out.println("Logger is created");
	}
	
	public void log(String message) {
		System.out.println("Log = "+message);
	}
	
	@PreDestroy
	public void destory() {
		System.out.println("Logger is destroyed");
	}
	
}
