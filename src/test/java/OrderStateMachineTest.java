import com.cicc.itgm.MyBatisPlusApplication;
import com.cicc.itgm.context.OrderContext;
import com.cicc.itgm.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyBatisPlusApplication.class)
public class OrderStateMachineTest {

    @Autowired
    private OrderService orderService;

    /**
     * 订单状态机依照主流程顺序流转
     */
    @Test
    public void testOrderStateMachineStraightForward() {
        Long orderId = 1L;
        String operator = "testUser";
        OrderContext orderContext = new OrderContext(orderId, operator);

        // 状态应该从CREATED变为PAID
        orderService.payOrder(orderContext);

        // 状态应该从PAID变为SHIPPED
        orderService.shipOrder(orderContext);

        // 状态应该从SHIPPED变为DELIVERED
        orderService.deliverOrder(orderContext);

        // 状态应从DELIVERED变为RETURNED
        orderService.returnOrder(orderContext);
    }

    /**
     * cancel取消订单之后，状态机就不会流转到之后的状态了
     */
    @Test
    public void testOrderStateMachineCancel() {
        Long orderId = 1L;
        String operator = "testUser";
        OrderContext orderContext = new OrderContext(orderId, operator);

        // 状态应该从CREATED变为CANCELLED
        orderService.cancelOrder(orderContext);

        // 状态应该从CREATED变为PAID
        orderService.payOrder(orderContext);

        // 状态应该从PAID变为SHIPPED
        orderService.shipOrder(orderContext);

        // 状态应该从SHIPPED变为DELIVERED
        orderService.deliverOrder(orderContext);

        // 状态应从DELIVERED变为RETURNED
        orderService.returnOrder(orderContext);
    }

    @Test
    public void testOrderStateMachineConditions1() {
        Long orderId = 1L;
        String operator = null;
        OrderContext orderContext = new OrderContext(orderId, operator);

        // when condition校验不通过，状态机流转失败
        orderService.shipOrder(orderContext);
    }

    @Test
    public void testOrderStateMachineConditions2() {
        OrderContext orderContext1 = new OrderContext(1L, "Kendrick");
        orderService.payOrder(orderContext1);

        // Paid -> Shipped 订单状态被拒绝：operator为null
        OrderContext orderContext2 = new OrderContext(1L, null);
        orderService.shipOrder(orderContext2);
    }

    @Test
    public void testOrderStateMachineConditions3() {
        OrderContext orderContext1 = new OrderContext(1L, "Kendrick");
        orderService.payOrder(orderContext1);

        // Paid -> Shipped 订单状态被拒绝：订单2L没有持久化过处于Created状态，无法执行Ship event
        OrderContext orderContext2 = new OrderContext(2L, "Rondo");
        orderService.shipOrder(orderContext2);
    }
}
