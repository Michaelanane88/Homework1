import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class VectorComparisonDemo {

    public static void run() {
        System.out.println("=== Vector vs ArrayList Comparison ===");

        Vector<Product> v = new Vector<>();
        ArrayList<Product> a = new ArrayList<>();

        int n = 10_000;

      
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            v.add(new Product("VP" + i, "Item" + i, "Demo", 1.0 + i, 10, "Supplier"));
        }
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a.add(new Product("AP" + i, "Item" + i, "Demo", 1.0 + i, 10, "Supplier"));
        }
        long t4 = System.nanoTime();

        
        Random rand = new Random(42);
        int reads = 1_000;

        long t5 = System.nanoTime();
        double sink1 = 0;
        for (int i = 0; i < reads; i++) {
            sink1 += v.get(rand.nextInt(v.size())).getPrice();
        }
        long t6 = System.nanoTime();

        rand = new Random(42);
        long t7 = System.nanoTime();
        double sink2 = 0;
        for (int i = 0; i < reads; i++) {
            sink2 += a.get(rand.nextInt(a.size())).getPrice();
        }
        long t8 = System.nanoTime();

      
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long memUsed = rt.totalMemory() - rt.freeMemory();

        System.out.printf("Vector add %d: %.2f ms%n", n, (t2 - t1) / 1_000_000.0);
        System.out.printf("ArrayList add %d: %.2f ms%n", n, (t4 - t3) / 1_000_000.0);
        System.out.printf("Vector random read %d: %.2f ms (sink=%.2f)%n", reads, (t6 - t5) / 1_000_000.0, sink1);
        System.out.printf("ArrayList random read %d: %.2f ms (sink=%.2f)%n", reads, (t8 - t7) / 1_000_000.0, sink2);
        System.out.printf("Approx memory used after GC: %.2f MB%n", memUsed / (1024.0 * 1024.0));

        System.out.println("\nSummary:");
        System.out.println("- Vector methods are synchronized (thread-safe), which can add overhead.");
        System.out.println("- ArrayList is usually faster in single-threaded use.");
        System.out.println("- Choose Vector when you need thread-safety or legacy Enumeration APIs.");
        System.out.println("- Choose ArrayList for most modern single-threaded scenarios.");
        System.out.println("=====================================");
    }
}
