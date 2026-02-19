import java.util.Vector;

public class OrderManager {
    private final Vector<Order> orders = new Vector<>();

    public void addOrder(Order order) {
        if (order == null) throw new IllegalArgumentException("order cannot be null");

        if (findOrder(order.getOrderId()) != null) {
            System.out.println("Duplicate orderId blocked: " + order.getOrderId());
            return;
        }
        orders.add(order);
    }

    public Order findOrder(String orderId) {
        if (orderId == null) return null;
        String id = orderId.trim();
        for (Order o : orders) {
            if (o.getOrderId().equals(id)) return o;
        }
        return null;
    }

    public Vector<Order> getOrdersByStatus(String status) {
        Vector<Order> result = new Vector<>();
        if (status == null) return result;

        String st = status.trim();
        for (Order o : orders) {
            if (o.getOrderStatus().equalsIgnoreCase(st)) result.add(o);
        }
        return result;
    }

    public Vector<Order> getOrdersByCustomer(String customerName) {
        Vector<Order> result = new Vector<>();
        if (customerName == null) return result;

        String name = customerName.trim();
        for (Order o : orders) {
            if (o.getCustomerName().equalsIgnoreCase(name)) result.add(o);
        }
        return result;
    }

    // Revenue from delivered orders only
    public double getTotalRevenue() {
        double total = 0.0;
        for (Order o : orders) {
            if (o.getOrderStatus().equalsIgnoreCase("Delivered")) {
                total += o.calculateTotal();
            }
        }
        return total;
    }

    public void cancelOrder(String orderId) {
        Order o = findOrder(orderId);
        if (o == null) {
            System.out.println("Order not found: " + orderId);
            return;
        }
        o.updateStatus("Cancelled");
    }

    public void printAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        for (Order o : orders) {
            o.printOrder();
        }
    }

    public Vector<Order> getPendingOrders() {
        return getOrdersByStatus("Pending");
    }

    public int getOrderCount() {
        return orders.size();
    }
}
