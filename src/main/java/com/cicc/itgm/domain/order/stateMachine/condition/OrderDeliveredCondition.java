package com.cicc.itgm.domain.order.stateMachine.condition;

import com.cicc.itgm.context.OrderContext;
import org.squirrelframework.foundation.fsm.Condition;

public class OrderDeliveredCondition implements Condition<OrderContext> {
    @Override
    public boolean isSatisfied(OrderContext context) {
        // 条件检查逻辑
        System.out.println("检查送达条件，订单ID: " + context.orderId());
        return context.orderId() != null && context.operator() != null;
    }

    @Override
    public String name() {
        return "OrderDeliveredCondition";
    }
}
