package caseStudy1;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Lazy
public class CreditCardPayment implements PaymentService{

	public CreditCardPayment() {
		System.out.println("Credit Card Payment");
	}
	
	@Override
	public void processPayment(double amount) {
		System.out.println("Processing payment using credit card: "+amount);
	}

}
