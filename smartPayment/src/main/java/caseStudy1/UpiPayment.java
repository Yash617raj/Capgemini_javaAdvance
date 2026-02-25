package caseStudy1;

import org.springframework.stereotype.Component;

@Component
public class UpiPayment implements PaymentService {

	public UpiPayment() {
		System.out.println("UPI payment");
	}
	
	@Override
	public void processPayment(double amount) {
		System.out.println("Process payment using UPI: "+amount);
		
	}

}
