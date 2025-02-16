import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.dao.UserEntity;
import com.cicc.itgm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        userMapper.selectList(null).forEach(System.out::println);
    }

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
}
