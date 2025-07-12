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
        basePackages = "com.cicc.itgm.repository.mongo.statistics",
        mongoTemplateRef = "statisticsMongoTemplate"
)
public class StatisticsMongoConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.statistics")
    public MongoProperties statisticsMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "statisticsMongoTemplate")
    public MongoTemplate statisticsMongoTemplate() throws Exception {
        return new MongoTemplate(statisticsMongoFactory(statisticsMongoProperties()));
    }

    @Bean
    public MongoDatabaseFactory statisticsMongoFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }
}
