package com.cicc.itgm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cicc.itgm.dao.mysql.UserEntity;
import com.cicc.itgm.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl实现了IService接口中的方法
 * UserServiceImpl继承了ServiceImpl 可根据业务override ServiceImpl中的逻辑实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
