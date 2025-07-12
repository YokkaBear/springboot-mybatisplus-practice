package com.cicc.itgm.repository.mongo.statistics;

import com.cicc.itgm.dao.mongo.statistics.RevenueDaily;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RevenueDailyRepository extends MongoRepository<RevenueDaily, String> {
}
