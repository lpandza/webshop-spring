package com.example.webshop.form;

import com.example.webshop.entity.Discount;
import com.example.webshop.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {

    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be positive number")
    private Double price;

    @Size(max = 250, message = "Address too long")
    private String address;

    @Email(message = "Enter valid email")
    private String email;

    @Size(max = 20, message = "Phone number too long")
    private String phoneNumber;

    private Discount discount;

    private List<Item> items;

}
