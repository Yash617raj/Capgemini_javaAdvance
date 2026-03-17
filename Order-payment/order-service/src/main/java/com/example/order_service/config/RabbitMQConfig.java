package com.example.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DIRECT_EXCHANGE = "order.direct.exchange";
    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String DIRECT_ROUTING_KEY = "order.created";

    // DirectExchange routes message using exact routing key matching
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    // Queue that payment service listens to for order payment processing
    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

    //Bind payment.queue to direct exchange with exact key "order.created"
    // Only message with routingKey = "order.created" will reach this queue
    @Bean
    public Binding directBinding() {
        return BindingBuilder
                .bind(paymentQueue())
                .to(directExchange())
                .with(DIRECT_ROUTING_KEY);
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
