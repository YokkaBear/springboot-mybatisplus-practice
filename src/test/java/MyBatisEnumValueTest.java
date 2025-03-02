import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.dao.UserEntity;
import com.cicc.itgm.enums.NationEnum;
import com.cicc.itgm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MyBatisEnumValueTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testEnumValue() {
        /*UserEntity user = UserEntity.builder()
                .name("HansenYang")
                .age(20)
                .email("HansYang@qq.com")
                .nation(NationEnum.CHINA)
                .isDeleted(0)
                .build();*/

        UserEntity user = UserEntity.builder()
                .name("Hachimura")
                .age(27)
                .email("hachim@qq.com")
                .nation(NationEnum.JAPAN)
                .isDeleted(0)
                .build();
        userMapper.insert(user);
    }
}
