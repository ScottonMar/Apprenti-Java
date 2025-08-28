package com.inventory.model;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PerishableProduct extends Product {
    private LocalDate expiryDate;

    public PerishableProduct() {}

    public PerishableProduct(String productId, String productName, int quantity, double price, LocalDate expiryDate) {
        super(productId, productName, quantity, price);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Expiry: %s", expiryDate);
    }
}
