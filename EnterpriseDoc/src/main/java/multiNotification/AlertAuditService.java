package multiNotification;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class AlertAuditService {
	
	public void alert(String message) {
		System.out.println("Alerted Message: "+message);
	}
}
