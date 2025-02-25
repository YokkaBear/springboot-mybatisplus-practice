import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.dao.UserEntity;
import com.cicc.itgm.mapper.UserMapper;
import com.cicc.itgm.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class MyBatisPlusQueryWrapperTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    public void testSelectAllByWrapper() {
        List<UserEntity> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        System.out.println(users.size());
    }

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

    @Test
    public void testOrderByWrapper() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<UserEntity> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testDeleteByWrapper() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNull("email");
        queryWrapper.ge("age", 29);
        int result = userMapper.delete(queryWrapper);
        System.out.println("影响行数：" + result);
    }

    @Test
    public void testWrapperPriority() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 30)
                .isNull("email");
        UserEntity user = UserEntity.builder()
                .age(32)
                .email("jbrown@gmail.com")
                .build();
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响行数：" + result);
    }

    @Test
    public void testSelectByMap() {
        // select查询返回指定字段
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> userMap = userMapper.selectMaps(queryWrapper);
        userMap.forEach(System.out::println);
    }

    @Test
    public void testSubQueryByWrapper() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.notInSql("id", "select id from user where id <= 3") // in类型子查询语句放在inSql，xx类型子查询语句放在xxSql
                .select("name", "age", "email");
        List<Map<String, Object>> userMap = userMapper.selectMaps(queryWrapper);
        userMap.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapper() {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("age", 32)
//                .set("email", "curry@gmail.com")
//                .eq("name", "Curry");
        updateWrapper.set("age", 33)
                .set("email", "dreamgreen@yahoo.com")
                .set("name", "draymondGreen")
                .eq("id", 17);
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void testSelectByCondition1() {
        // 用户输入/前端传入的某个查询条件可能为空 如何处理
        String userName = null;
        Integer ageStart = 20;
        Integer ageEnd = 30;
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        // 直接的if-else判断
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.eq("name", userName);
        }
        if (ageStart != null) {
            queryWrapper.ge("age", ageStart);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<UserEntity> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByCondition2() {
        // 用户输入/前端传入的某个查询条件可能为空 如何处理
        String userName = null;
        Integer ageStart = 20;
        Integer ageEnd = null;
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        // 用api自带的判断条件
        queryWrapper.eq(StringUtils.isNotBlank(userName), "name", userName)
                .ge(ageStart != null, "age", ageStart)
                .le(ageEnd != null, "age", ageEnd);
        List<UserEntity> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testLambdaQueryWrapper() {
        // 在查询条件中引入lambda表达式
        String userName = "Curry";
        Integer ageStart = 20;
        Integer ageEnd = null;
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        // 用api自带的判断条件
        queryWrapper.eq(StringUtils.isNotBlank(userName), UserEntity::getName, userName)
                .ge(ageStart != null, UserEntity::getAge, ageStart)
                .le(ageEnd != null, UserEntity::getAge, ageEnd);
        List<UserEntity> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testLambdaUpdateWrapper() {
        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(UserEntity::getAge, 36)
                .set(UserEntity::getEmail, "pointGuardChefCurry@gmail.com")
                .and(it -> it.eq(UserEntity::getName, "Curry").or().isNull(UserEntity::getEmail)); // lambda表达式内的逻辑优先运算
        int result = userMapper.update(UserEntity.builder().build(), updateWrapper);
        System.out.println("影响行数：" + result);
    }

    @Test
    public void testPage() {
        // 设置分页参数
        Page<UserEntity> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);
        // 查看返回结果
        List<UserEntity> users = page.getRecords();
        users.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }
}
