package multiNotification;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SmsNotification implements NotificationChannel {

	@Override
	public void sendAlert(String message) {
		System.out.println("Sms Notification: "+message);
	}

}
