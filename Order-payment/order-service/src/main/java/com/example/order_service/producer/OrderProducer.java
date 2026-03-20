package com.example.order_service.producer;

import com.example.order_service.config.RabbitMQConfig;
import com.example.order_service.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {
    // This class is used to send message to the RabbitMQ
    private final RabbitTemplate rabbitTemplate;

    // publishes to Direct exchange with exact routing key "order.created", only payment.queue receives this
    public void sendToDirectExchange(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.DIRECT_EXCHANGE, // target exchange
                RabbitMQConfig.DIRECT_ROUTING_KEY, // exact routing key "order.created"
                event // message payload
        );
    }

    public void sendToTopicExchange(OrderCreatedEvent event, String region) {
        String routingKey = "order."+region;
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TOPIC_EXCHANGE,
                routingKey,
                event
        );
    }
}
