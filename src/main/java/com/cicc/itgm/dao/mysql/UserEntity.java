package com.cicc.itgm.dao.mysql;

import com.baomidou.mybatisplus.annotation.*;
import com.cicc.itgm.enums.NationEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("user")
public class UserEntity {
    @TableId
    private long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("email")
    private String email;

    @TableField("nation")
    private NationEnum nation;

    @TableLogic
    private Integer isDeleted;
}
