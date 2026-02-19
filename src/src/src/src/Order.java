import java.util.Vector;

public class Order {
    private String orderId;
    private String customerName;
    private String orderDate; 
    private Vector<OrderItem> items = new Vector<>();
    private String orderStatus = "Pending";

    public Order(String orderId, String customerName, String orderDate) {
        setOrderId(orderId);
        setCustomerName(customerName);
        setOrderDate(orderDate);
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) {
        if (orderId == null || orderId.trim().isEmpty())
            throw new IllegalArgumentException("orderId cannot be empty");
        this.orderId = orderId.trim();
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty())
            throw new IllegalArgumentException("customerName cannot be empty");
        this.customerName = customerName.trim();
    }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) {
        if (orderDate == null || orderDate.trim().isEmpty())
            throw new IllegalArgumentException("orderDate cannot be empty");
        this.orderDate = orderDate.trim();
    }

    public String getOrderStatus() { return orderStatus; }

    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        items.add(item);
    }

    public boolean removeItem(String productId) {
        OrderItem found = findItem(productId);
        if (found == null) return false;
        return items.remove(found);
    }

    public OrderItem findItem(String productId) {
        if (productId == null) return null;
        String id = productId.trim();
        for (OrderItem it : items) {
            if (it.getProductId().equals(id)) return it;
        }
        return null;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem it : items) {
            total += it.calculateSubtotal();
        }
        return total;
    }

    public int getTotalItems() {
        int totalQty = 0;
        for (OrderItem it : items) totalQty += it.getQuantity();
        return totalQty;
    }

    public void updateStatus(String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            System.out.println("Status update rejected (empty).");
            return;
        }
        this.orderStatus = newStatus.trim();
    }

    public void printOrder() {
        System.out.println("=== Order " + orderId + " ===");
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + orderStatus);

        if (items.isEmpty()) {
            System.out.println("No items.");
        } else {
            for (OrderItem it : items) {
                System.out.println("  - " + it);
            }
        }

        System.out.printf("Total: $%.2f%n", calculateTotal());
        System.out.println("===================");
    }

    public Vector<OrderItem> getItems() {
        return new Vector<>(items); // copy
    }

    public String toString() {
        return String.format("Order{id='%s', customer='%s', date='%s', status='%s', items=%d, total=%.2f}",
                orderId, customerName, orderDate, orderStatus, items.size(), calculateTotal());
    }
}
