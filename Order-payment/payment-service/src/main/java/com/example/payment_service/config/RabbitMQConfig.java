package com.example.payment_service.config;

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

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

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
