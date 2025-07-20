package com.cicc.itgm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * author: wangyoujia
 * description: OrderEvent 订单状态机事件枚举类
 * date: 2025/7/20
 */
@AllArgsConstructor
@Getter
public enum OrderEvent {
    /**
     * 支付
     */
    PAY("支付"),

    /**
     * 发货
     */
    SHIP("发货"),

    /**
     * 送达
     */
    DELIVER("送达"),

    /**
     * 取消
     */
    CANCEL("取消"),

    /**
     * 退货
     */
    RETURN("退货");

    private final String desc;
}
