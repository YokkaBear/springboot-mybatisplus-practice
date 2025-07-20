package com.cicc.itgm.domain.order.stateMachine;

import com.cicc.itgm.context.OrderContext;
import com.cicc.itgm.domain.order.stateMachine.condition.*;
import com.cicc.itgm.enums.OrderEvent;
import com.cicc.itgm.enums.OrderState;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;

import javax.annotation.Resource;

@StateMachineParameters(stateType = OrderState.class,
        eventType = OrderEvent.class,
        contextType = OrderContext.class)
@Transitions({
        @Transit(from = "CREATED", to = "PAID", on = "PAY", callMethod = "fromCreatedToPaid", when = OrderPaymentCondition.class),
        @Transit(from = "PAID", to = "SHIPPED", on = "SHIP", callMethod = "fromPaidToShipped", when = OrderShippedCondition.class),
        @Transit(from = "SHIPPED", to = "DELIVERED", on = "DELIVER", callMethod = "fromShippedToDelivered", when = OrderDeliveredCondition.class),
        @Transit(from = "CREATED", to = "CANCELLED", on = "CANCEL", callMethod = "fromCreatedToCancelled", when = OrderCancelledCondition.class),
        @Transit(from = "DELIVERED", to = "RETURNED", on = "RETURN", callMethod = "fromDeliveredToReturned", when = OrderReturnedCondition.class)
})
public class OrderStateMachine extends AbstractStateMachine<OrderStateMachine, OrderState, OrderEvent, OrderContext> {
    @Override
    protected void afterTransitionCompleted(OrderState fromState, OrderState toState,
                                            OrderEvent event, OrderContext context) {
        // 状态转换完成后自动持久化
        OrderStateMachineRegister.saveMachine(context.orderId(), this);
    }

    @Override
    protected void afterTransitionDeclined(OrderState fromState, OrderEvent event,
                                           OrderContext context) {
        // 状态转换被拒绝时也可以记录日志等
        System.out.println("状态转换被拒绝: "+fromState+" -> "+event);
    }

    // 定义callMethod 状态转换方法
    // mock支付后的业务逻辑
    protected void fromCreatedToPaid(OrderState from, OrderState to, OrderEvent event, OrderContext context) {
        System.out.println("订单" + context.orderId() + "从" + from + "状态转换为" + to + "状态");
    }

    // mock发货后的业务逻辑
    protected void fromPaidToShipped(OrderState from, OrderState to, OrderEvent event, OrderContext context) {
        System.out.println("订单" + context.orderId() + "从" + from + "状态转换为" + to + "状态");
    }

    // mock送达后的业务逻辑
    protected void fromShippedToDelivered(OrderState from, OrderState to, OrderEvent event, OrderContext context) {
        System.out.println("订单" + context.orderId() + "从" + from + "状态转换为" + to + "状态");
    }

    // mock取消后的业务逻辑
    protected void fromCreatedToCancelled(OrderState from, OrderState to, OrderEvent event, OrderContext context) {
        System.out.println("订单" + context.orderId() + "从" + from + "状态转换为" + to + "状态");
    }

    // mock退货后的业务逻辑
    protected void fromDeliveredToReturned(OrderState from, OrderState to, OrderEvent event, OrderContext context) {
        System.out.println("订单" + context.orderId() + "从" + from + "状态转换为" + to + "状态");
    }

    // 可以添加其他状态机方法
    public void logCurrentState(OrderContext context) {
        System.out.println("订单" + context.orderId() + "当前状态: " + getCurrentState());
    }
}
