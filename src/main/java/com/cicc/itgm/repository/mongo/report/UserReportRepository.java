package com.cicc.itgm.repository.mongo.report;

import com.cicc.itgm.dao.mongo.report.UserReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserReportRepository extends MongoRepository<UserReport, String> {
}
