package com.example.webshop.service;

import com.example.webshop.entity.Order;
import com.example.webshop.form.OrderForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAll();

    Optional<Order> save(Order order);

}
