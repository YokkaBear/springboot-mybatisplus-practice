import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.dao.mysql.ProductEntity;
import com.cicc.itgm.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MyBatisConcurrentLockTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testConcurrentWithoutOptimisticLock() {
        // 未对version字段通过@Version注解加乐观锁
        // 模拟并发查询 用户1/用户2查询时价格均为15000
        ProductEntity p1 = productMapper.selectById(1L);
        System.out.println("用户1查询价格：" + p1.getPrice());

        ProductEntity p2 = productMapper.selectById(1L);
        System.out.println("用户2查询价格：" + p2.getPrice());

        // 模拟并发修改
        p1.setPrice(p1.getPrice() + 3000);
        productMapper.updateById(p1); // 用户1修改后的价格：18000


        p2.setPrice(p2.getPrice() - 2000);
        productMapper.updateById(p2); // 用户2修改后的价格：13000，同时也为未加锁情况下商品1的最终价格

        // 模拟同步修改后的查询结果
        ProductEntity p3 = productMapper.selectById(1L);
        System.out.println(p3.getPrice()); // 13000，相较于成本价减少了2000元
    }

    @Test
    public void testConcurrentWithOptimisticLock() {
        // 模拟并发查询
        ProductEntity p1 = productMapper.selectById(1L);
        System.out.println("用户1查询价格：" + p1.getPrice()); // (price, version): (13000, 1)

        ProductEntity p2 = productMapper.selectById(1L);
        System.out.println("用户2查询价格：" + p2.getPrice()); // (13000, 1)

        // 模拟并发修改
        // 用户1执行并发修改
        p1.setPrice(p1.getPrice() + 3000);
        productMapper.updateById(p1); // 用户1修改后：(16000, 2)

        // 用户2执行并发修改
        p2.setPrice(p2.getPrice() - 2000);
        int result = productMapper.updateById(p2); // update ... where id = 1 and version = 0，更新时由于找不到旧的版本号将导致更新失败
        if (result == 0) { // 乐观锁 自旋，失败后重新走获取锁的流程
            p2 = productMapper.selectById(1L); // (16000, 2) entity中的版本号在重新查询后更新
            p2.setPrice(p2.getPrice() - 2000);
            result = productMapper.updateById(p2); // 用户2修改成功后：(14000, 3)，version随着update更新+1而+1
        }
        System.out.println("用户2修改失败，二次修改后的结果：" + result);

        // 查询最终修改结果
        ProductEntity p3 = productMapper.selectById(1L);
        System.out.println("最终修改后的价格为：" + p3.getPrice()); // 14000
    }
}
