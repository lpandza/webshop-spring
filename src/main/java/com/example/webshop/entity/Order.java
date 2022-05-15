package com.example.webshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    private Double price;

    private Double priceWithDiscount;

    private String address;

    private String email;

    private String phoneNumber;

    @ManyToOne
    private Discount discount;

    @ManyToMany
    @JoinTable(
            name = "order_has_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    public Order(Date createdAt, Double price, Double priceWithDiscount, String address, String email, String phoneNumber, Discount discount, List<Item> items) {
        this.createdAt = createdAt;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.discount = discount;
        this.items = items;
    }
}
