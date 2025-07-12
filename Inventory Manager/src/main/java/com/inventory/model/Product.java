package com.inventory.model;

public class Product {
    private String productID;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productID, String productName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayInfo() {
        System.out.printf("ID: %s | Name: %s | Quantity: %d | Price: $%.2f%n",
                productID, productName, quantity, price);
    }

    public String toCSV() {
        return String.format("%s,%s,%d,%.2f", productID, productName, quantity, price);
    }

    public static Product fromCSV(String line) {
        String[] parts = line.split(",");
        return new Product(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
    }
}
