package com.cicc.itgm.dao.mongo.report;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "user_report")
public class UserReport {
    private String id;
    private String name;
    private String keyId; // 一般report存储在对象存储，这里只保存keyId
}
