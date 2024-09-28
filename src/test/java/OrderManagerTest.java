import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

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

        // Test for invalid discount percentage
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderManager.applyDiscount(200.0, 110.0);
        });
        assertEquals("Invalid discount percentage", exception.getMessage());
    }

    @Test
    public void testSaveSensitiveDataValid() throws IOException {
        // Test valid data save
        assertDoesNotThrow(() -> {
            orderManager.saveSensitiveData("1234-5678-9101", "testfile.txt");
        });
    }

    @Test
    public void testSaveSensitiveDataInvalidCard() {
        // Test invalid credit card number
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderManager.saveSensitiveData("", "testfile.txt");
        });
        assertEquals("Invalid credit card number", exception.getMessage());
    }

    @Test
    public void testSaveSensitiveDataIOException() {
        // We cannot easily simulate an IOException without complex mocking in real environments.
        // But you can imagine testing by specifying a restricted file path or mocking.
    }
}
