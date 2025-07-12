package com.cicc.itgm.dao.mongo.statistics;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Document(collection = "profit_daily")
public class ProfitDaily {
    private String id;
    private LocalDate date;
    private Double amount;
}
