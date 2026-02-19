import java.util.Vector;

public class InventorySystemMain {
    public static void main(String[] args) {
        // Part 1 demo
        ProductInventory inventory = new ProductInventory();
        inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
        inventory.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
        inventory.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));

        inventory.printAllProducts();
        inventory.printCapacityInfo();

        Vector<Product> electronics = inventory.getProductsByCategory("Electronics");
        System.out.println("Electronics: " + electronics.size());

        Vector<Product> lowStock = inventory.getLowStockProducts(10);
        System.out.println("Low stock items: " + lowStock.size());

        System.out.printf("Total inventory value: $%.2f%n", inventory.getTotalInventoryValue());

        // Part 3.1 capacity management demo
        inventory.printCapacityReport();
        inventory.ensureCapacity(50);
        inventory.printCapacityReport();
        inventory.optimizeCapacity();
        inventory.printCapacityReport();

        // Part 3.2 enumeration demo
        System.out.println("\nProducts via Enumeration:");
        inventory.printProductsUsingEnumeration();

        // Part 2 demo
        OrderManager orderManager = new OrderManager();

        Order order1 = new Order("O001", "Alice", "2024-01-15");
        order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
        order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
        orderManager.addOrder(order1);

        Order order2 = new Order("O002", "Bob", "2024-01-16");
        order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
        orderManager.addOrder(order2);

        // Make revenue non-zero
        order1.updateStatus("Delivered");

        orderManager.printAllOrders();
        System.out.printf("Total revenue: $%.2f%n", orderManager.getTotalRevenue());

        // Part 4.3 bounded generics demo
        Vector<Integer> ints = new Vector<>();
        ints.add(10);
        ints.add(20);
        ints.add(30);
        System.out.println("Sum: " + VectorUtils.sumNumbers(ints));      // 60.0
        System.out.println("Average: " + VectorUtils.averageNumbers(ints)); // 20.0
        System.out.println("Max int: " + VectorUtils.findMax(ints));      // 30

    
        GenericContainer<String> stringContainer = new GenericContainer<>();
        stringContainer.add("Hello");
        stringContainer.add("World");
        System.out.println("String container size: " + stringContainer.size());

        GenericContainer<Product> productContainer = new GenericContainer<>();
        productContainer.add(new Product("P100", "Keyboard", "Electronics", 49.99, 12, "TechCorp"));
        System.out.println("Product container size: " + productContainer.size());

        Vector<Product> all = inventory.getAllProductsCopy();
        Vector<Product> filtered = VectorUtils.filter(all, new VectorUtils.Predicate<Product>() {
            @Override
            public boolean test(Product p) {
                return p.getCategory().equalsIgnoreCase("Electronics");
            }
        });
        System.out.println("Filtered Electronics count: " + filtered.size());

      
        VectorComparisonDemo.run();
    }
}
