package com.cap.mockityCommerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrder_Success() {

        when(paymentGateway.processPayment(2000.0)).thenReturn(true);

        String result = orderService.placeOrder(2000.0);

        assertEquals("Order Confirmed", result);

        verify(paymentGateway, times(1)).processPayment(2000.0);
    }

    @Test
    void testPlaceOrder_PaymentFailure() {

        when(paymentGateway.processPayment(1500.0)).thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(1500.0)
        );

        assertEquals("Payment Failed", exception.getMessage());

        verify(paymentGateway, times(1)).processPayment(1500.0);
    }

    @Test
    void testPlaceOrder_InvalidAmount() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.placeOrder(0.0)
        );

        assertEquals("Invalid Order Amount", exception.getMessage());

        verify(paymentGateway, never()).processPayment(anyDouble());
    }
}