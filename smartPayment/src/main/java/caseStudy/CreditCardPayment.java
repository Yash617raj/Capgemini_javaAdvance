package caseStudy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CreditCardPayment implements PaymentGateways {

	@Override
	public void processPayment(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Payment through Credit Card: "+amount);
	}

}
