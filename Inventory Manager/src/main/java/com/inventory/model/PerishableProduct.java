package com.inventory.model;

import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expiryDate;

    public PerishableProduct(String productID, String productName, int quantity, double price, LocalDate expiryDate) {
        super(productID, productName, quantity, price);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Expiry Date: " + expiryDate);
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + expiryDate;
    }

    public static PerishableProduct fromCSV(String line) {
        String[] parts = line.split(",");
        return new PerishableProduct(parts[0], parts[1],
                Integer.parseInt(parts[2]), Double.parseDouble(parts[3]),
                LocalDate.parse(parts[4]));
    }
}
