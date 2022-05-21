package com.example.webshop.facade;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.OrderDto;
import com.example.webshop.entity.Item;
import com.example.webshop.form.OrderForm;

import java.util.List;
import java.util.Optional;

public interface OrderFacade {

    List<OrderDto> getAll();

    Optional<OrderDto> save(OrderForm orderForm);
}
