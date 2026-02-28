package docEngines;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class AuditService {
	
	@PostConstruct
	public void init() {
		System.out.println("Initializing audit config");
	}
	
	public void log(String mes) {
		System.out.println("Log = "+mes);
	}
	
	@PreDestroy
	public void destory() {
		System.out.println("Releasing audit config");
	}
}
