# Homework1

# Homework 1: Vector-Based Inventory Management System

## Overview
This project implements an Inventory Management System using **Java Vectors** to manage:
- Products (inventory)
- Orders and order items
- Vector-specific features (capacity management, Enumeration)
- Generics utilities and a generic container
- A Vector vs ArrayList performance comparison demo

## Files / Classes
- `Product.java` — Product model (equals/hashCode by `productId`, implements Comparable for max demo)
- `ProductInventory.java` — Inventory manager using `Vector<Product>` + capacity tools + Enumeration demo
- `OrderItem.java` — Line item in an order
- `Order.java` — Order with `Vector<OrderItem>`, totals, status, printing
- `OrderManager.java` — Manages `Vector<Order>` + filtering + revenue
- `VectorUtils.java` — Generic utilities: swap, findMax, countMatches, filter, sum/average numbers
- `GenericContainer.java` — Generic wrapper around `Vector<T>`
- `VectorComparisonDemo.java` — Simple benchmark: Vector vs ArrayList
- `InventorySystemMain.java` — Runs demos of the full system

## Build / Run Instructions

### Option 1: Local build (recommended)
From the project root:
```bash
javac -d out src/*.java
java -cp out InventorySystemMain
