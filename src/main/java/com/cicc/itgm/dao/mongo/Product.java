package com.cicc.itgm.dao.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "product_info")
public class Product {
    private String id;
    private String name;
    private String prodLoc;
    private LocalDateTime prodTime;
    private String desc;
}
