package multiNotification;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(appConfig.class);
		
		AlertManager alertManager = context.getBean(AlertManager.class);
		alertManager.triggerAlert("Hello world");
		context.close();
	}
}
