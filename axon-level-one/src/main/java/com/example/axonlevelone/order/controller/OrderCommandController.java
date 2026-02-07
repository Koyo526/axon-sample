package com.example.axonlevelone.order.controller;

import com.example.axonlevelone.order.command.CreateOrderCommand;
import com.example.axonlevelone.order.controller.dto.CreateOrderRequest;
import com.example.axonlevelone.order.controller.dto.CreateOrderResponse;
import com.example.axonlevelone.order.controller.dto.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderCommandController {

    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/orders")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        commandGateway.sendAndWait(new CreateOrderCommand(orderId, request.productName()));

        return new CreateOrderResponse(orderId, OrderStatus.CREATED);
    }
}
