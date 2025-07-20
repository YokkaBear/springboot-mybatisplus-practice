package com.cicc.itgm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * author: wangyoujia
 * description: OrderState 订单状态机状态枚举类
 * date: 2025/7/20
 */
@AllArgsConstructor
@Getter
public enum OrderState {
    /**
     * 已创建
     */
    CREATED("已创建"),

    /**
     * 已支付
     */
    PAID("已支付"),

    /**
     * 已发货
     */
    SHIPPED("已发货"),

    /**
     * 已送达
     */
    DELIVERED("已送达"),

    /**
     * 已取消
     */
    CANCELLED("已取消"),

    /**
     * 已退货
     */
    RETURNED("已退货");

    private final String desc;
}
