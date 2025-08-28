package com.inventory.model;

import java.util.Objects;

public class Product {
    protected String productId;
    protected String productName;
    protected int quantity;
    protected double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    public void increaseStock(int amount) { this.quantity += amount; }
    public void decreaseStock(int amount) { this.quantity -= amount; }

    @Override
    public String toString() {
        return String.format("%s | %s | %d | $%.2f", productId, productName, quantity, price);
    }

    public String toFileString() {
        return String.format("N,%s,%s,%d,%.2f", productId, productName, quantity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
