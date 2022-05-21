package com.example.webshop.facade.impl;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.ProfitByQuarterDto;
import com.example.webshop.entity.Item;
import com.example.webshop.facade.ReportFacade;
import com.example.webshop.service.ReportService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReportFacadeImpl implements ReportFacade {

    private final ReportService reportService;

    public ReportFacadeImpl(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public Optional<ItemDto> getBestSellingItemLastWeek() {
        return reportService.getBestSellingItemLastWeek().map(this::toItemDto);
    }

    @Override
    public Optional<ProfitByQuarterDto> getQuarterlyProfitByYear(Integer year) {
        return reportService.getQuarterlyProfitByYear(year);
    }

    private ItemDto toItemDto(Item item){
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity(), item.getBrand());
    }
}
