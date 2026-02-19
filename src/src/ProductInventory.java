import java.util.Enumeration;
import java.util.Vector;

public class ProductInventory {
    private final Vector<Product> products = new Vector<>();

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("product cannot be null");

        if (findProduct(product.getProductId()) != null) {
            System.out.println("Duplicate productId blocked: " + product.getProductId());
            return;
        }
        products.add(product);
    }

    public boolean removeProduct(String productId) {
        Product p = findProduct(productId);
        if (p == null) return false;
        return products.remove(p);
    }

    public Product findProduct(String productId) {
        if (productId == null) return null;
        String id = productId.trim();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getProductId().equals(id)) return p;
        }
        return null;
    }

  
    public Vector<Product> getProductsByCategory(String category) {
        Vector<Product> result = new Vector<>();
        if (category == null) return result;

        String cat = category.trim();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(cat)) result.add(p);
        }
        return result;
    }

    public Vector<Product> getLowStockProducts(int threshold) {
        Vector<Product> result = new Vector<>();
        for (Product p : products) {
            if (p.getQuantityInStock() < threshold) result.add(p);
        }
        return result;
    }

    
    public double getTotalInventoryValue() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantityInStock();
        }
        return total;
    }

    public void updateStock(String productId, int quantityChange) {
        Product p = findProduct(productId);
        if (p == null) {
            System.out.println("Product not found: " + productId);
            return;
        }

        int newQty = p.getQuantityInStock() + quantityChange;
        if (newQty < 0) {
            System.out.println("Stock update rejected (would go negative). productId=" + productId);
            return;
        }
        p.setQuantityInStock(newQty);
    }

    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-6s | %-18s | %-12s | %-10s | %-6s | %-15s%n",
                "ID", "Name", "Category", "Price", "Stock", "Supplier");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (Product p : products) {
            System.out.printf("%-6s | %-18s | %-12s | $%-9.2f | %-6d | %-15s%n",
                    p.getProductId(), p.getName(), p.getCategory(), p.getPrice(),
                    p.getQuantityInStock(), p.getSupplier());
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    public int getTotalProducts() {
        return products.size();
    }

  
    public void printCapacityInfo() {
        System.out.println("Vector size: " + products.size());
        System.out.println("Vector capacity: " + products.capacity());
    }


    public void optimizeCapacity() {
        products.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) throw new IllegalArgumentException("minCapacity cannot be negative");
        products.ensureCapacity(minCapacity);
    }

    public void printCapacityReport() {
        int size = products.size();
        int cap = products.capacity();
        double utilization = (cap == 0) ? 0.0 : (100.0 * size / cap);
        int remaining = cap - size;

        System.out.println("=== Capacity Report ===");
        System.out.println("Size: " + size);
        System.out.println("Capacity: " + cap);
        System.out.printf("Utilization: %.2f%%%n", utilization);
        System.out.println("Can add before resize: " + remaining);
        System.out.println("=======================");
    }

    public void printProductsUsingEnumeration() {
        
        Enumeration<Product> e = products.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }

    public Vector<Product> getAllProductsCopy() {
        return new Vector<>(products);
    }
}
