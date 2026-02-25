package caseStudy1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcesser {

    private PaymentService creditCardPayment;   
    private PaymentService upiPayment;

    @Autowired
    private TransactionLogger transactionLogger; 

    @Autowired
    public PaymentProcesser(PaymentService creditCardPayment, @Qualifier("upiPayment") PaymentService upiPayment) {
        this.creditCardPayment = creditCardPayment;
        this.upiPayment = upiPayment;
    }

    public void makeCreditCardPayment(double amount) {
        creditCardPayment.processPayment(amount);
        transactionLogger.log("Credit Card Payment completed for: " + amount);
    }

    public void makeUpiPayment(double amount) {
        upiPayment.processPayment(amount);
        transactionLogger.log("UPI Payment completed for: " + amount);
    }
}