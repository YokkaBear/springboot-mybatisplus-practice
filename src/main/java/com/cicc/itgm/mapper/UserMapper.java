package com.cicc.itgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cicc.itgm.dao.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
