package com.example.webshop.service;

import com.example.webshop.dto.ProfitByQuarterDto;
import com.example.webshop.entity.Item;

import java.util.Optional;

public interface ReportService {
    Optional<Item> getBestSellingItemCurrentWeek();

    Optional<ProfitByQuarterDto> getQuarterlyProfitByYear(Integer year);
}
