package com.cicc.itgm.dao.mysql;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@TableName("product")
@Data
public class ProductEntity {
    @TableId
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("price")
    private Integer price;

    @Version // 对version字段添加乐观锁
    @TableField("version")
    private Integer version;

}
