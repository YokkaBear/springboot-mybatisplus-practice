package com.cicc.itgm.service;

import com.cicc.itgm.dao.mongo.Product;
import com.cicc.itgm.dao.mongo.User;
import com.cicc.itgm.dao.mongo.report.ProductReport;
import com.cicc.itgm.dao.mongo.report.UserReport;
import com.cicc.itgm.dao.mongo.statistics.ProfitDaily;
import com.cicc.itgm.dao.mongo.statistics.RevenueDaily;
import com.cicc.itgm.repository.mongo.primary.PrimaryRepository;
import com.cicc.itgm.repository.mongo.report.ProductReportRepository;
import com.cicc.itgm.repository.mongo.report.UserReportRepository;
import com.cicc.itgm.repository.mongo.secondary.SecondaryRepository;
import com.cicc.itgm.repository.mongo.statistics.ProfitDailyRepository;
import com.cicc.itgm.repository.mongo.statistics.RevenueDailyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MongoServiceImpl implements MongoService {
    private final PrimaryRepository primaryRepository;
    private final SecondaryRepository secondaryRepository;
    private final ProductReportRepository productReportRepository;
    private final UserReportRepository userReportRepository;
    private final ProfitDailyRepository profitDailyRepository;
    private final RevenueDailyRepository revenueDailyRepository;

    public MongoServiceImpl(
        PrimaryRepository primaryRepository,
        SecondaryRepository secondaryRepository,
        ProductReportRepository productReportRepository,
        UserReportRepository userReportRepository,
        ProfitDailyRepository profitDailyRepository,
        RevenueDailyRepository revenueDailyRepository
    ) {
        this.primaryRepository = primaryRepository;
        this.secondaryRepository = secondaryRepository;
        this.productReportRepository = productReportRepository;
        this.userReportRepository = userReportRepository;
        this.profitDailyRepository = profitDailyRepository;
        this.revenueDailyRepository = revenueDailyRepository;
    }

    public void saveToPrimary() {
        User userEntity = this.buildUserEntity();
        primaryRepository.save(userEntity);
    }

    public void saveToSecondary() {
        Product productEntity = this.buildProductEntity();
        secondaryRepository.save(productEntity);
    }

    public void saveToProductReport() {
        ProductReport prEntity = ProductReport.builder()
                .id(UUID.randomUUID().toString())
                .name("CocaCola")
                .keyId("qwerty")
                .build();
        productReportRepository.save(prEntity);
    }

    public void saveToUserReport() {
        UserReport urEntity = UserReport.builder()
                .id(UUID.randomUUID().toString())
                .name("Elizabeth")
                .keyId("asdfgh")
                .build();
        userReportRepository.save(urEntity);
    }

    public void saveToRevenueDaily() {
        RevenueDaily rdEntity = RevenueDaily.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDate.now())
                .amount(5000000.0)
                .build();
        revenueDailyRepository.save(rdEntity);
    }

    public void saveToProfitDaily() {
        ProfitDaily pdEntity = ProfitDaily.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDate.now())
                .amount(2000000.0)
                .build();
        profitDailyRepository.save(pdEntity);
    }

    private User buildUserEntity() {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name("Tatum")
                .age("28")
                .gender("male")
                .address("Boston")
                .build();
    }

    private Product buildProductEntity() {
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .name("Sprint")
                .prodLoc("Portland")
                .prodTime(LocalDateTime.now())
                .desc("beverage")
                .build();
    }

}
