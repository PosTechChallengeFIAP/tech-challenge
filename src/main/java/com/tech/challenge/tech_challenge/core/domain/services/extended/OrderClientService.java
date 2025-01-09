package com.tech.challenge.tech_challenge.core.domain.services.extended;

import com.tech.challenge.tech_challenge.adapters.driven.infra.repositories.OrderRepository;
import com.tech.challenge.tech_challenge.core.application.exceptions.ResourceNotFoundException;
import com.tech.challenge.tech_challenge.core.application.exceptions.ValidationException;
import com.tech.challenge.tech_challenge.core.domain.entities.Client;
import com.tech.challenge.tech_challenge.core.domain.entities.Order;
import com.tech.challenge.tech_challenge.core.domain.services.OrderService;
import com.tech.challenge.tech_challenge.core.domain.useCases.FindClientByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderClientService extends OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FindClientByIdUseCase findClientByIdUseCase;

    public Order addClientToOrder(UUID orderId, UUID clientId) throws ValidationException, ResourceNotFoundException {
        Order order = getById(orderId);
        Client client = findClientByIdUseCase.execute(clientId);
        client.validate();

        order.setClient(client);
        order.validate();

        return orderRepository.save(order);
    }
}
