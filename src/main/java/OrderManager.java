import java.io.FileWriter;
import java.io.IOException;

public class OrderManager {

    private int totalOrders;
    private double totalRevenue;

    public OrderManager() {
        this.totalOrders = 0;
        this.totalRevenue = 0.0;
    }

    public void addOrder(double orderAmount) {
        totalOrders++;
        totalRevenue += orderAmount;
    }

    public void cancelOrder(double orderAmount) {
        if (totalOrders > 0) {
            totalOrders--;
            totalRevenue -= orderAmount;
        }
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getAverageOrderValue() {
        if (totalOrders == 0) {
            return 0.0;
        }
        return totalRevenue / totalOrders;
    }

    public double applyDiscount(double orderAmount, double discountPercentage) {
        if (discountPercentage > 100 || discountPercentage < 0) {
            throw new IllegalArgumentException("Invalid discount percentage");
        }
        return orderAmount - (orderAmount * discountPercentage / 100);
    }

    public boolean isRevenueExceeding(double limit) {
        return totalRevenue > limit;
    }

    public void resetOrders() {
        totalOrders = 0;
        totalRevenue = 0.0;
    }

    public void refundOrder(double orderAmount) {
        totalRevenue -= orderAmount;
    }

    public double increaseOrderAmount(double orderAmount, double percentage) {
        return orderAmount + (orderAmount * percentage / 100);
    }

    // Modify saveSensitiveData to explicitly throw exceptions more clearly
    public void saveSensitiveData(String creditCardNumber, String filePath) throws IOException {
        if (creditCardNumber == null || creditCardNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid credit card number");
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Credit Card Number: " + creditCardNumber);  // Sensitive data
        }
    }
}
