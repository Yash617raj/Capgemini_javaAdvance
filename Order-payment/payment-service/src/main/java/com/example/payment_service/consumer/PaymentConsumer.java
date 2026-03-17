package com.example.payment_service.consumer;

import com.example.payment_service.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    @RabbitListener(queues = "payment.queue")
    public void processPayment(OrderCreatedEvent event) {
        System.out.println("[DIRECT] Processing Payment for Order: "+event.getOrderId());
        System.out.println("[DIRECT] Amount: "+ event.getAmount());
        System.out.println("[DIRECT] Payment Sucessful");

    }
}
