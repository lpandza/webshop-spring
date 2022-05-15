package com.example.webshop.controller;

import com.example.webshop.dto.OrderDto;
import com.example.webshop.facade.OrderFacade;
import com.example.webshop.form.OrderForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping
    public List<OrderDto> getAll(){
        return orderFacade.getAll();
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(@Valid @RequestBody OrderForm orderForm){
        return orderFacade.save(orderForm)
                .map((orderDto) -> ResponseEntity.status(HttpStatus.CREATED).body(orderDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

}
