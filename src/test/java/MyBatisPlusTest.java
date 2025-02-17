import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.dao.UserEntity;
import com.cicc.itgm.mapper.UserMapper;
import com.cicc.itgm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelectList() {
        userMapper.selectList(null).forEach(System.out::println);
    }

    // 增 insert
    @Test
    public void testInsert() {
        UserEntity user = UserEntity.builder()
                .name("Michael")
                .age(28)
                .email("michael@qq.com")
                .build();
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println("id is " + user.getId());
    }

    // 删 delete
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(8L);
        System.out.println("影响行数：" + result);
    }

    @Test
    public void testDeleteBatchIds() {
        List<Long> idList = Arrays.asList(6L, 7L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("影响行数：" + result);
    }

    @Test
    public void testDeleteMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Michael");
        map.put("age", 28);
        int result = userMapper.deleteByMap(map);
        System.out.println("影响行数：" + result);
    }

    // 改 update
    @Test
    public void testUpdateId() {
        UserEntity user = UserEntity.builder()
                .id(10L)
                .name("Michael")
                .age(29)
                .email("mike@qq.com")
                .build();
        int result = userMapper.updateById(user);
        System.out.println("影响行数：" + result);
    }

    // 查 select
    @Test
    public void testSelectById() {
        UserEntity user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectByBatchIds() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L, 4L);
        List<UserEntity> users = userMapper.selectBatchIds(idList);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Sandy");
//        map.put("age", 21);
        map.put("name", "Michael");
        map.put("email", "mike@qq.com");
        List<UserEntity> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectAll() {
        List<UserEntity> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 测试IService+ServiceImpl实现service层默认逻辑
    @Test
    public void testGetCount() {
        long count = userService.count();
        System.out.println("数据库记录总条数为：" + count);
    }

    @Test
    public void testSaveBatch() {
        List<UserEntity> userList = new ArrayList<>();
        List<String> userNames = Arrays.asList("Tatum", "James", "Irving", "Doncic", "Jokic");
        for(int i = 0; i < 5; i++) {
            UserEntity user = UserEntity.builder()
                    .name(userNames.get(i))
                    .age(20 + i)
                    .email(userNames.get(i) + "@qq.com")
                    .build();
            userList.add(user);
        }
        boolean result = userService.saveBatch(userList);
        System.out.println("saveBatch result is: " + result);
    }
}
