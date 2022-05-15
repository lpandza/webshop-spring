package com.example.webshop.service.impl;

import com.example.webshop.entity.Order;
import com.example.webshop.form.OrderForm;
import com.example.webshop.repository.OrderRepository;
import com.example.webshop.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of(orderRepository.save(order));
    }
}
