package caseStudy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailNotification implements NotificationServices{

	@Override
	public void sendMessage(String message) {
		System.out.println("Notification through Email "+message);
	}
	
}
