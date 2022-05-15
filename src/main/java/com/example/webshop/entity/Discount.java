package com.example.webshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Double discountPercentage;

    private Boolean isUsed;

    public Discount(String code, Double discountPercentage, Boolean isUsed) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.isUsed = isUsed;
    }
}
