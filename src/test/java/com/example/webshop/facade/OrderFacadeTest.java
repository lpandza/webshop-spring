package com.example.webshop.facade;

import com.example.webshop.dto.OrderDto;
import com.example.webshop.entity.Brand;
import com.example.webshop.entity.Item;
import com.example.webshop.form.OrderForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderFacadeTest {

    @MockBean
    private OrderFacade orderFacade;

    @Test
    void saveOrder() throws Exception{
        Brand brand = new Brand("Intel");
        Item item = new Item(1L, "Intel i5", "desc", 550D, 15, brand);
        Item item2 = new Item(1L, "Intel i7", "desc", 1550D, 5, brand);
        OrderForm orderForm = new OrderForm(
                2100D,
                "address",
                "mail@mail.com",
                "1234",
                null,
                List.of(item, item2));

        OrderDto orderDto = new OrderDto(
                1L,
                2100D,
                2100D,
                "address",
                "mail@mail.com",
                "1234",
                List.of(item, item2));

        when(orderFacade.save(orderForm)).thenReturn(Optional.of(orderDto));

        Optional<OrderDto> orderDto1 = orderFacade.save(orderForm);

        assertEquals(orderDto1.get().getPrice(), orderForm.getPrice());
        assertEquals(orderDto1.get().getAddress(), orderForm.getAddress());
        assertEquals(orderDto1.get().getEmail(), orderForm.getEmail());
        assertEquals(orderDto1.get().getItems(), orderForm.getItems());
        assertEquals(orderDto1.get().getPhoneNumber(), orderForm.getPhoneNumber());


    }
}
