package com.cicc.itgm.repository.mongo.primary;

import com.cicc.itgm.dao.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {
}
