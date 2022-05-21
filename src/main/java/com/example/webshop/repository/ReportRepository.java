package com.example.webshop.repository;

import com.example.webshop.dto.ProfitByQuarterDto;
import com.example.webshop.entity.Item;

import java.util.Optional;

public interface ReportRepository {
    Optional<Item> getBestSellingItemLastWeek();

    Optional<ProfitByQuarterDto> getQuarterlyProfitByYear(Integer year);
}
