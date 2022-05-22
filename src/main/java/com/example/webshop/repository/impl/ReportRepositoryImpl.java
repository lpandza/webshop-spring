package com.example.webshop.repository.impl;

import com.example.webshop.dto.ProfitByQuarterDto;
import com.example.webshop.entity.Item;
import com.example.webshop.repository.ReportRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final EntityManager entityManager;

    public ReportRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Item> getBestSellingItemCurrentWeek() {
        Query query = entityManager.createNativeQuery(
                "SELECT i.*\n" +
                        "FROM orders o\n" +
                        "JOIN order_has_item ohi ON o.id = ohi.order_id\n" +
                        "JOIN item i ON ohi.item_id = i.id\n" +
                        "WHERE yearweek(o.created_at) = yearweek(now())\n" +
                        "GROUP BY 1\n" +
                        "ORDER BY count(*) DESC\n" +
                        "LIMIT 1;", Item.class
        );

        Item bestSellingItem = (Item) query.getSingleResult();

        return Optional.of(bestSellingItem);
    }

    @Override
    public Optional<ProfitByQuarterDto> getQuarterlyProfitByYear(Integer year) {
        Query query = entityManager.createNativeQuery(
                "SELECT sum(CASE WHEN quarter(o.created_at) = 1 THEN o.price_with_discount ELSE 0 END ) as q1,\n" +
                        "       sum(CASE WHEN quarter(o.created_at) = 2 THEN o.price_with_discount ELSE 0 END ) as q2,\n" +
                        "       sum(CASE WHEN quarter(o.created_at) = 3 THEN o.price_with_discount ELSE 0 END ) as q3,\n" +
                        "       sum(CASE WHEN quarter(o.created_at) = 4 THEN o.price_with_discount ELSE 0 END ) as q4\n" +
                        "FROM orders o\n" +
                        "WHERE year(o.created_at) = :yeartmp"
        );

        query.setParameter("yeartmp", year);

        List<Object[]> resultList = query.getResultList();

        ProfitByQuarterDto profitByQuarterDto = new ProfitByQuarterDto();
        resultList.forEach((record) -> {
            profitByQuarterDto.setQ1((BigDecimal) record[0]);
            profitByQuarterDto.setQ2((BigDecimal) record[1]);
            profitByQuarterDto.setQ3((BigDecimal) record[2]);
            profitByQuarterDto.setQ4((BigDecimal) record[3]);

        });

        return Optional.of(profitByQuarterDto);
    }
}
