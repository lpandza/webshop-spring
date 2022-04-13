package com.example.webshop.dto;

import com.example.webshop.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Brand brand;

}
