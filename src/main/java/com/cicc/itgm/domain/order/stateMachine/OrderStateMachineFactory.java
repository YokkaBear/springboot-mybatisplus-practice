package com.cicc.itgm.domain.order.stateMachine;

import com.cicc.itgm.context.OrderContext;
import com.cicc.itgm.enums.OrderEvent;
import com.cicc.itgm.enums.OrderState;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderStateMachineFactory {
    @Bean(name = "orderStateMachineBuilder")
    public StateMachineBuilder<OrderStateMachine, OrderState, OrderEvent, OrderContext> orderStateMachineBuilder() {
        // 使用注解方式自动构建状态机
        return StateMachineBuilderFactory.create(OrderStateMachine.class, OrderState.class, OrderEvent.class, OrderContext.class);
    }

    @Bean(name = "orderStateMachine")
    public OrderStateMachine orderStateMachine(StateMachineBuilder<OrderStateMachine, OrderState, OrderEvent, OrderContext> builder) {
        // 创建状态机实例，初始状态为CREATED
        return builder.newStateMachine(OrderState.CREATED);
    }
}
