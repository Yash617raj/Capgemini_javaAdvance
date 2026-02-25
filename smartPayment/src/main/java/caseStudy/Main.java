package caseStudy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);

	        PaymentService service = context.getBean(PaymentService.class);

	        service.processPayment(5000);
	        System.out.println();
	        
	        service.processPayment(3000);
	        context.close();
	}
}
