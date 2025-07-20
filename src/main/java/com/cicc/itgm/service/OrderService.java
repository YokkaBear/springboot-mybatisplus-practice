package com.cicc.itgm.service;

import com.cicc.itgm.context.OrderContext;
import com.cicc.itgm.domain.order.stateMachine.OrderStateMachine;
import com.cicc.itgm.domain.order.stateMachine.OrderStateMachineRegister;
import com.cicc.itgm.enums.OrderEvent;
import org.springframework.stereotype.Service;

/**
 * 核心思想：传参中的context(OrderContext)相当于ofa中的tradeEvent，machine.fire相当于调用trdClient.save(tradeEvent)
 */
@Service
public class OrderService {
    // machine交给machineRegister管理后，service和状态机的交互就直接通过register了。直接注入的machine被直接使用的话永远是初始状态，状态机逻辑就会有问题。
//    @Autowired
//    private OrderStateMachine orderStateMachine;

//    public void payOrder(Long orderId, String operator) {
//        OrderContext context = new OrderContext(orderId, operator);
//        orderStateMachine.fire(OrderEvent.PAY, context);
//        orderStateMachine.logCurrentState();
//    }
    public void payOrder(OrderContext orderContext) {
        OrderStateMachine machine = OrderStateMachineRegister.getMachine(orderContext.orderId());
        if (machine == null) {
            machine = OrderStateMachineRegister.createNewMachine(orderContext.orderId());
        }
        machine.fire(OrderEvent.PAY, orderContext);
        machine.logCurrentState(orderContext);
    }

    public void shipOrder(OrderContext orderContext) {
        OrderStateMachine machine = OrderStateMachineRegister.getMachine(orderContext.orderId());
        if (machine == null) {
            machine = OrderStateMachineRegister.createNewMachine(orderContext.orderId());
        }
        machine.fire(OrderEvent.SHIP, orderContext);
        machine.logCurrentState(orderContext);
    }

    public void deliverOrder(OrderContext orderContext) {
        OrderStateMachine machine = OrderStateMachineRegister.getMachine(orderContext.orderId());
        if (machine == null) {
            machine = OrderStateMachineRegister.createNewMachine(orderContext.orderId());
        }
        machine.fire(OrderEvent.DELIVER, orderContext);
        machine.logCurrentState(orderContext);
    }

    public void cancelOrder(OrderContext orderContext) {
        OrderStateMachine machine = OrderStateMachineRegister.getMachine(orderContext.orderId());
        if (machine == null) {
            machine = OrderStateMachineRegister.createNewMachine(orderContext.orderId());
        }
        machine.fire(OrderEvent.CANCEL, orderContext);
        machine.logCurrentState(orderContext);
    }

    public void returnOrder(OrderContext orderContext) {
        OrderStateMachine machine = OrderStateMachineRegister.getMachine(orderContext.orderId());
        if (machine == null) {
            machine = OrderStateMachineRegister.createNewMachine(orderContext.orderId());
        }
        machine.fire(OrderEvent.RETURN, orderContext);
        machine.logCurrentState(orderContext);
    }
}
