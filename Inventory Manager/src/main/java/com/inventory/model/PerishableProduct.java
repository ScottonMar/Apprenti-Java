package com.inventory.model;

import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expiryDate;

    public PerishableProduct(String productId, String productName, int quantity, double price, LocalDate expiryDate) {
        super(productId, productName, quantity, price);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Expires: " + expiryDate;
    }

    @Override
    public String toFileString() {
        return String.format("P,%s,%s,%d,%.2f,%s", productId, productName, quantity, price, expiryDate);
    }
}
