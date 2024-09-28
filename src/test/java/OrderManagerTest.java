import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    public void testSaveSensitiveDataValid() throws IOException {
        // Test saving sensitive data (valid case)
        assertDoesNotThrow(() -> {
            orderManager.saveSensitiveData("1234-5678-9101", "testfile.txt");
        });
    }

    @Test
    public void testSaveSensitiveDataInvalidCard() {
        // Test saving sensitive data with an invalid credit card number, no exception thrown
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderManager.saveSensitiveData("", "testfile.txt");  // This test now doesn't handle null properly
        });
        assertEquals("Invalid credit card number", exception.getMessage());
    }
}
