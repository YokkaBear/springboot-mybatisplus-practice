package com.cicc.itgm.config.mongo;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.cicc.itgm.repository.mongo.report",
        mongoTemplateRef = "reportMongoTemplate"
)
public class ReportMongoConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.report")
    public MongoProperties reportMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "reportMongoTemplate")
    public MongoTemplate reportMongoTemplate() throws Exception {
        return new MongoTemplate(reportMongoFactory(reportMongoProperties()));
    }

    @Bean
    public MongoDatabaseFactory reportMongoFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }
}
