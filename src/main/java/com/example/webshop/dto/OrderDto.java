package com.example.webshop.dto;

import com.example.webshop.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Double price;
    private Double priceWithDiscount;
    private String address;
    private String email;
    private String phoneNumber;
    private List<Item> items;

}
