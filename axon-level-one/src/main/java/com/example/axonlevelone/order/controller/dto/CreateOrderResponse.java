package com.example.axonlevelone.order.controller.dto;

public record CreateOrderResponse(String orderId, OrderStatus status) {
}
