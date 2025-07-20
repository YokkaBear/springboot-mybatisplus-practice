package com.cicc.itgm.domain.order.stateMachine;

import com.cicc.itgm.context.OrderContext;
import com.cicc.itgm.enums.OrderEvent;
import com.cicc.itgm.enums.OrderState;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderStateMachineRegister {
    private static final Map<Long, OrderStateMachine> ORDER_STATE_MACHINE_HASHMAP = new HashMap<>();

    public static OrderStateMachine getMachine(Long orderId) {
        return ORDER_STATE_MACHINE_HASHMAP.get(orderId);
    }

    public static void saveMachine(Long orderId, OrderStateMachine machine) {
        ORDER_STATE_MACHINE_HASHMAP.put(orderId, machine);
    }

    public static OrderStateMachine createNewMachine(Long orderId) {
        OrderStateMachine machine = StateMachineBuilderFactory
                .create(OrderStateMachine.class, OrderState.class, OrderEvent.class, OrderContext.class)
                .newStateMachine(OrderState.CREATED);
        ORDER_STATE_MACHINE_HASHMAP.put(orderId, machine);
        return machine;
    }
}
