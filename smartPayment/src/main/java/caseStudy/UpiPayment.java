package caseStudy;

import org.springframework.stereotype.Component;

@Component
public class UpiPayment implements PaymentGateways{

	@Override
	public void processPayment(double amount) {
		System.out.println("Payment done through UPI: "+amount);
		
	}

}
