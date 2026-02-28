package multiNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AlertManager {
	 private final NotificationChannel notificationChannel;
	    private final RateLimiter rateLimiter;

	    private AlertAuditService alertAuditService;

	    @Autowired
	    public AlertManager(@Qualifier("pushNotification") NotificationChannel notificationChannel,RateLimiter rateLimiter) {
	        this.notificationChannel = notificationChannel;
	        this.rateLimiter = rateLimiter;
	    }

	    @Autowired
	    public void setAlertAuditService(AlertAuditService alertAuditService) {
	        this.alertAuditService = alertAuditService;
	    }
	    

	    public void triggerAlert(String message) {
	        alertAuditService.alert("Alert triggered: " + message);
	        rateLimiter.rate(message);
	        System.out.println("Alert processing completed.");
	    }
	
}

