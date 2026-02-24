package BeanLifeCycle;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class DbConnection {
	
	public DbConnection() {
		System.out.println("DB Constuctor is called");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Init Method is being called");
	}
	
	public void executeQuery() {
		System.out.println("Query is being executed");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Destroy is being called");
	}
}
