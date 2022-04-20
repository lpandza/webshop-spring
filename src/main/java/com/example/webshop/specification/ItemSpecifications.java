package com.example.webshop.specification;

import com.example.webshop.entity.Item;
import com.example.webshop.form.ItemFilterForm;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItemSpecifications {

    public static Specification<Item> priceLt(ItemFilterForm itemFilterForm){
        return (root, query, criteriaBuilder) -> {
            if (!Objects.isNull(itemFilterForm.getMaxPrice())){
                return criteriaBuilder.lessThan(root.get("price"), itemFilterForm.getMaxPrice());
            }
            return null;
        };
    }

    public static Specification<Item> priceGt(ItemFilterForm itemFilterForm){
        return (root, query, criteriaBuilder) -> {
            if (!Objects.isNull(itemFilterForm.getMinPrice())){
                return criteriaBuilder.greaterThan(root.get("price"), itemFilterForm.getMinPrice());
            }
            return null;
        };
    }

    public static Specification<Item> containsBrand(ItemFilterForm itemFilterForm){
        return (root, query, criteriaBuilder) -> {
            if (!Objects.isNull(itemFilterForm.getBrands()) && itemFilterForm.getBrands().length > 0){
                return criteriaBuilder.or(Arrays
                        .stream(itemFilterForm.getBrands())
                        .map(id -> criteriaBuilder.equal(root.join("brand").get("id"), id))
                        .toArray(Predicate[]::new));
            }
            return null;
        };
    }

}
