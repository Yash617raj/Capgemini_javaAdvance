package com.cap.mockityCommerce;

public interface PaymentGateway {
    boolean processPayment(double amount);
}