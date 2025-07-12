package com.cicc.itgm.repository.mongo.secondary;

import com.cicc.itgm.dao.mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<Product, String> {
}
