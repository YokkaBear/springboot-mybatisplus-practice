package com.cicc.itgm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cicc.itgm.dao.mysql.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity> {
}
