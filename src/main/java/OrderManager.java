import java.io.FileWriter;
import java.io.IOException;

public class OrderManager {

    private int totalOrders;  // vague variable names could impact maintainability
    private double totalRevenue;

    // Large constructor doing too much (adding code that's unnecessary)
    public OrderManager() {
        this.totalOrders = 0;
        this.totalRevenue = 0.0;
        // Redundant logging that has no real purpose
        System.out.println("OrderManager initialized with 0 totalOrders and 0.0 totalRevenue.");
    }

    // Method has vague naming, does multiple things, and includes redundant code
    public void bigMethod() {
        totalOrders++;   // increasing total orders
        totalOrders--;   // decreasing total orders again, just to create duplication
        totalRevenue += 50.0; // random increase in revenue
        System.out.println("Executed bigMethod that altered both orders and revenue."); // Unnecessary logging
    }

    public void addOrder(double orderAmount) {
        totalOrders++;
        totalRevenue += orderAmount;
        bigMethod();  // Calling a method that does redundant things
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

    // Duplicate logic between applyDiscount and increaseOrderAmount methods
    public double applyDiscount(double orderAmount, double discountPercentage) {
        if (discountPercentage > 100 || discountPercentage < 0) {
            throw new IllegalArgumentException("Invalid discount percentage");
        }
        return orderAmount - (orderAmount * discountPercentage / 100);
    }

    public double increaseOrderAmount(double orderAmount, double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Invalid percentage");
        }
        return orderAmount + (orderAmount * percentage / 100);
    }

    // No error handling for null or empty input; storing sensitive data without encryption
    public void saveSensitiveData(String creditCardNumber, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Credit Card Number: " + creditCardNumber);  // Sensitive data stored as plain text
        } catch (IOException e) {
            // Printing stack trace without proper error handling
            e.printStackTrace();  // SonarCloud will flag this
        }
    }
}
