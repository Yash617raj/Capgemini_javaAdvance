package com.example.payment_service.consumer;

import com.example.payment_service.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RegionConsumer {
    @RabbitListener(queues = "order.india.queue")
    public void handleIndiaOrder(OrderCreatedEvent event){
        System.out.println("[TOPIC - INDIA] Received Order: "+event.getOrderId());
        System.out.println("[TOPIC - INDIA] Amount: "+event.getAmount());
        System.out.println("[TOPIC - INDIA] Applying India regional processing...");
    }

    @RabbitListener(queues = "order.usa.queue")
    public void handleUsaOrder(OrderCreatedEvent event){
        System.out.println("[TOPIC - USA] Received Order: "+event.getOrderId());
        System.out.println("[TOPIC - USA] Amount: "+event.getAmount());
        System.out.println("[TOPIC - USA] Applying Usa regional processing...");
    }
}
