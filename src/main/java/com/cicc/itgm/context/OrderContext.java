package com.cicc.itgm.context;

/**
 * 订单状态机上下文类
 * @param orderId
 * @param operator
 */
public record OrderContext(Long orderId, String operator) {
}
