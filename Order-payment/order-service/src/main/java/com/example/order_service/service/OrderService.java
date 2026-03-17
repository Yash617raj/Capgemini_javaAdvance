package com.example.order_service.service;

import com.example.order_service.dto.OrderCreatedEvent;
import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.producer.OrderProducer;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    // OrderProducer bundles all RabbitMQ publishing - keeps messaging logic out of here
    private final OrderProducer orderProducer;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        // Save order to DB
        Order order = new Order();
        order.setProductName(dto.getProductName());
        order.setQuantity(dto.getQuantity());
        order.setPrice(dto.getPrice());
        order.setStatus("CREATED");
        Order saved = repository.save(order);

        // Build the event payload to send via RabbitMQ
        OrderCreatedEvent event = new OrderCreatedEvent(saved.getId(), saved.getPrice());

        // publish to direct Exchange - exact routing
        orderProducer.sendToDirectExchange(event);

        // publish to topic exchange - region-based routing
//        orderProducer.sendToTopicExchange(event,dto.getRegion());

        return new OrderResponseDTO(saved.getId(),"ORDER_CREATED");
    }
}
