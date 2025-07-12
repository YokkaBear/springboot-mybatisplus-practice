import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.service.MongoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MongoDBTest {
    @Autowired
    private MongoServiceImpl mongoService;

    @Test
    public void testPrimaryRepository() {
        mongoService.saveToPrimary();
    }

    @Test
    public void testSecondaryRepository() {
        mongoService.saveToSecondary();
    }

    @Test
    public void testUserReportRepository() {
        mongoService.saveToUserReport();
    }

    @Test
    public void testProductReportRepository() {
        mongoService.saveToProductReport();
    }

    @Test
    public void testRevenueDailyRepository() {
        mongoService.saveToRevenueDaily();
    }

    @Test
    public void testProfitDailyRepository() {
        mongoService.saveToProfitDaily();
    }
}
