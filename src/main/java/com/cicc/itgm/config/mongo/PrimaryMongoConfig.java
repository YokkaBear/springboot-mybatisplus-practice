package com.cicc.itgm.config.mongo;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.cicc.itgm.repository.mongo.primary",
        mongoTemplateRef = "primaryMongoTemplate"
)
public class PrimaryMongoConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.primary")
    public MongoProperties primaryMongoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(primaryMongoFactory(primaryMongoProperties()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory primaryMongoFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }
}
