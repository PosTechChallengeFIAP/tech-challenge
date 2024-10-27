package com.tech.challenge.tech_challenge.adapters.driver.controllers;

import com.tech.challenge.tech_challenge.core.domain.entities.Order;
import com.tech.challenge.tech_challenge.core.domain.entities.OrderItem;
import com.tech.challenge.tech_challenge.core.domain.entities.Payment;
import com.tech.challenge.tech_challenge.core.domain.services.OrderService;
import com.tech.challenge.tech_challenge.core.domain.services.order.AddClientToOrderService;
import com.tech.challenge.tech_challenge.core.domain.services.order.AddPaymentToOrderService;
import com.tech.challenge.tech_challenge.core.domain.services.order.UpdateOrderPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    AddClientToOrderService addClientToOrderService;

    @GetMapping("/order")
    public List<Order> all(){
        return orderService.list();
    }

    @GetMapping("/order/{id}")
    public Order one(@PathVariable UUID id) throws Exception {
        return orderService.getById(id);
    }

    @PostMapping("/order")
    public Order create(@RequestBody Order order) throws Exception {
        return orderService.create(order);
    }
    
    @PostMapping("/order/{orderId}/client/{clientId}")
    public Order create(@PathVariable(name = "orderId") UUID orderId, @PathVariable(name = "clientId") UUID clientId) throws Exception {
        return addClientToOrderService.handle(orderId, clientId);
    }
}
