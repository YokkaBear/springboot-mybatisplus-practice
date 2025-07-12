package com.cicc.itgm.dao.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "user_info")
public class User {
    private String id;
    private String name;
    private String age;
    private String gender;
    private String address;
}
