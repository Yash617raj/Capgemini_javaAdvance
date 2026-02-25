package caseStudy1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);
		
		PaymentProcesser processor = context.getBean(PaymentProcesser.class);
		processor.makeCreditCardPayment(5000);
		System.out.println();
		processor.makeUpiPayment(4000);
		context.close();
	}
}
