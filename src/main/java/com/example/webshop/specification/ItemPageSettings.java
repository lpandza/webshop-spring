package com.example.webshop.specification;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ItemPageSettings {

    private Integer page = 1;
    private Integer itemsPerPage = 10;
    private String direction = "asc";
    private String key = "id";

    public Sort buildSort() {
        return switch (direction) {
            case "desc" -> Sort.by(key).descending();
            case "asc" -> Sort.by(key).ascending();
            default -> Sort.by(key).descending();
        };
    }
}
