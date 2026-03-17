package com.example.payment_service.dto;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private double amount;
}
