package com.inventory.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PerishableProductTest {

    @Test
    void testPerishableProductCreation() {
        LocalDate expiry = LocalDate.of(2025, 12, 31);
        PerishableProduct pp = new PerishableProduct("PX", "Yogurt", 20, 2.99, expiry);

        assertEquals("PX", pp.getProductID());
        assertEquals("Yogurt", pp.getProductName());
        assertEquals(20, pp.getQuantity());
        assertEquals(2.99, pp.getPrice());
        assertEquals(expiry, pp.getExpiryDate());
    }

    @Test
    void testCSVConversion() {
        LocalDate expiry = LocalDate.of(2025, 11, 15);
        PerishableProduct original = new PerishableProduct("PY", "Milk", 10, 3.25, expiry);

        String csv = original.toCSV();
        PerishableProduct copy = PerishableProduct.fromCSV(csv);

        assertEquals(original.getProductID(), copy.getProductID());
        assertEquals(original.getProductName(), copy.getProductName());
        assertEquals(original.getQuantity(), copy.getQuantity());
        assertEquals(original.getPrice(), copy.getPrice());
        assertEquals(original.getExpiryDate(), copy.getExpiryDate());
    }
}
