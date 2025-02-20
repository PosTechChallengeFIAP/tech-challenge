package com.tech.challenge.tech_challenge.core.domain.useCases.RemoveItemToOrderUseCase;

import com.tech.challenge.tech_challenge.core.domain.entities.Order;
import com.tech.challenge.tech_challenge.core.domain.repositories.IOrderRepository;
import com.tech.challenge.tech_challenge.core.domain.useCases.FindOrderByIdUseCase.FindOrderByIdUseCase;
import com.tech.challenge.tech_challenge.core.domain.useCases.FindOrderItemByIdUseCase.FindOrderItemByIdUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveItemToOrderUseCase implements IRemoveItemToOrderUseCase {

    @Autowired
    private FindOrderByIdUseCase findOrderByIdUseCase;

    @Autowired
    private FindOrderItemByIdUseCase findOrderItemByIdUseCase;

    @Autowired
    private IOrderRepository orderRepository;

    public Order execute(UUID orderId, UUID itemId) {
        Order order = findOrderByIdUseCase.execute(orderId);
        order.removeItem(findOrderItemByIdUseCase.execute(order, itemId));

        return orderRepository.save(order);
    }
}
