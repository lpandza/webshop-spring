package com.example.webshop.facade.impl;

import com.example.webshop.dto.OrderDto;
import com.example.webshop.entity.Order;
import com.example.webshop.facade.OrderFacade;
import com.example.webshop.form.OrderForm;
import com.example.webshop.service.DiscountService;
import com.example.webshop.service.ItemService;
import com.example.webshop.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final ItemService itemService;
    private final DiscountService discountService;

    public OrderFacadeImpl(OrderService orderService, ItemService itemService, DiscountService discountService) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.discountService = discountService;
    }

    @Override
    public List<OrderDto> getAll() {
        return orderService.getAll().stream().map(this::toOrderDto).toList();
    }

    @Transactional
    @Override
    public Optional<OrderDto> save(OrderForm orderForm) {
        changeItemQuantity(orderForm);
        updateDiscountIsUsed(orderForm);
        return orderService.save(toOrder(orderForm)).map(this::toOrderDto);
    }

    private void updateDiscountIsUsed(OrderForm orderForm){
        if (!isDiscountNull(orderForm)){
            Boolean isUsed = orderForm.getDiscount().getIsUsed();
            orderForm.getDiscount().setIsUsed(!isUsed);
            discountService.save(orderForm.getDiscount());
        }
    }

    private void changeItemQuantity(OrderForm orderForm){
        orderForm.getItems()
                .forEach(item -> {
                    item.setQuantity(item.getQuantity() - 1);
                    itemService.save(item);
                });
    }

    private Double calculatePriceWithDiscount(OrderForm orderForm){
        if (isDiscountNull(orderForm)) return orderForm.getPrice();
        Double fullPrice = orderForm.getPrice();

        return fullPrice - (fullPrice * (orderForm.getDiscount().getDiscountPercentage()) / 100);
    }

    private boolean isDiscountNull(OrderForm orderForm) {
        return Objects.isNull(orderForm.getDiscount());
    }

    private OrderDto toOrderDto(Order order){
        return new OrderDto(
                order.getId(),
                order.getPrice(),
                order.getPriceWithDiscount(),
                order.getAddress(),
                order.getEmail(),
                order.getPhoneNumber(),
                order.getItems()
        );
    }

    private Order toOrder(OrderForm orderForm){
        return new Order(
                new Date(System.currentTimeMillis()),
                orderForm.getPrice(),
                calculatePriceWithDiscount(orderForm),
                orderForm.getAddress(),
                orderForm.getEmail(),
                orderForm.getPhoneNumber(),
                orderForm.getDiscount(),
                orderForm.getItems()
        );
    }
}
