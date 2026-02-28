package multiNotification;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class PushNotification implements NotificationChannel{

	@Override
	public void sendAlert(String message) {
		System.out.println("Push Notification: "+message);
	}

}
