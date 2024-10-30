package com.tech.challenge.tech_challenge.core.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OrderBuilder {

    private UUID id;
    private Payment payment;
    private List<OrderItem> orderItems;
    private EOrderStatus status;

    public OrderBuilder(){
        id = UUID.randomUUID();
        status = EOrderStatus.PAYMENT_PENDING;

        payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setSatus(EPaymentStatus.PENDING);
        payment.setValue(60.0);

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setPrice(30.0);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(UUID.randomUUID());
        orderItem.setProduct(product);
        orderItem.setQuantity(2);

        orderItems = new ArrayList<>();
        orderItems.add(orderItem);
    }

    public OrderBuilder withId(UUID id){
        this.id = id;
        return this;
    }

    public OrderBuilder withPayment(Payment payment){
        this.payment = payment;
        return this;
    }

    public OrderBuilder withStatus(EOrderStatus status){
        this.status = status;
        return this;
    }

    public OrderBuilder withOrderItem(OrderItem orderItem){
        this.orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        return this;
    }

    public OrderBuilder withOrderItems(List<OrderItem> orderItems){
        this.orderItems = orderItems;
        return this;
    }

    public OrderBuilder addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        return this;
    }

    public Order build(){
        Order order = new Order();
        order.setId(id);
        order.setPayment(payment);
        order.setStatus(status);

        for(OrderItem orderItem : orderItems){
            order.addItem(orderItem);
        }

        return order;
    }
}