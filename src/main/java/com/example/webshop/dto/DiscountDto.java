package com.example.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountDto {

    private Long id;
    private String code;
    private Double discountPercentage;
    private Boolean isUsed;

}
