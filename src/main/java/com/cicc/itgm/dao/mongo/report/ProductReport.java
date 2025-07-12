package com.cicc.itgm.dao.mongo.report;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "product_report")
public class ProductReport {
    private String id;
    private String name;
    private String keyId;
}
