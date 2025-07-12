package com.cicc.itgm.repository.mongo.statistics;

import com.cicc.itgm.dao.mongo.statistics.ProfitDaily;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfitDailyRepository extends MongoRepository<ProfitDaily, String> {
}
