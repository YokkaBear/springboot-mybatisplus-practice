import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.dao.UserEntity;
import com.cicc.itgm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MyBatisPlusQueryWrapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectWrapper() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.ne("name", "Tatum");
        queryWrapper.between("age", 20, 30)
                .isNotNull("email")
                .likeRight("name", "J");
        List<UserEntity> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
}
