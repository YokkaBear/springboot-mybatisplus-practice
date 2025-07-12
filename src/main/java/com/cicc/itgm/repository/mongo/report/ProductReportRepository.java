package com.cicc.itgm.repository.mongo.report;

import com.cicc.itgm.dao.mongo.report.ProductReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductReportRepository extends MongoRepository<ProductReport, String> {
}
