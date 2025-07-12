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
        basePackages = "com.cicc.itgm.repository.mongo.secondary",
        mongoTemplateRef = "secondaryMongoTemplate"
)
public class SecondaryMongoConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryMongoFactory(secondaryMongoProperties()));
    }

    @Bean
    public MongoDatabaseFactory secondaryMongoFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }
}
