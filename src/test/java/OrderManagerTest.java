import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderManagerTest {

    private OrderManager orderManager;

    @BeforeEach
    public void setUp() {
        orderManager = new OrderManager();
    }

    @Test
    public void testAddOrder() {
        orderManager.addOrder(100.0);
        assertEquals(1, orderManager.getTotalOrders());
        assertEquals(100.0, orderManager.getTotalRevenue());
    }

    @Test
    public void testCancelOrder() {
        orderManager.addOrder(50.0);
        orderManager.cancelOrder(50.0);
        assertEquals(0, orderManager.getTotalOrders());
        assertEquals(0.0, orderManager.getTotalRevenue());
    }

    @Test
    public void testGetAverageOrderValue() {
        orderManager.addOrder(100.0);
        orderManager.addOrder(200.0);
        assertEquals(150.0, orderManager.getAverageOrderValue());
    }

    @Test
    public void testApplyDiscount() {
        double discountedAmount = orderManager.applyDiscount(200.0, 10.0);
        assertEquals(180.0, discountedAmount);
    }

    @Test
    public void testIsRevenueExceeding() {
        orderManager.addOrder(500.0);
        assertTrue(orderManager.isRevenueExceeding(300.0));
        assertFalse(orderManager.isRevenueExceeding(600.0));
    }
}
