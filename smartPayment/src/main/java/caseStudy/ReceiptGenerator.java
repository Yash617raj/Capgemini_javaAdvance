package caseStudy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ReceiptGenerator {
	public void generateReceipt(String message) {
		System.out.println("Generating Script "+message);
		System.out.println("hashcode: "+this.hashCode());
	}
}
