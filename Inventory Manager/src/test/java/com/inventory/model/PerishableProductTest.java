package com.inventory.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PerishableProductTest {

    @Test
    void testPerishableProductCreation() {
        LocalDate expiry = LocalDate.of(2025, 12, 31);
        PerishableProduct pp = new PerishableProduct("010", "Milk", 30, 2.50, expiry);

        assertEquals("010", pp.getProductId());
        assertEquals("Milk", pp.getProductName());
        assertEquals(30, pp.getQuantity());
        assertEquals(2.50, pp.getPrice());
        assertEquals(expiry, pp.getExpiryDate());
    }

    @Test
    void testToStringIncludesExpiry() {
        PerishableProduct pp = new PerishableProduct("011", "Yogurt", 10, 3.25, LocalDate.of(2025, 10, 1));
        assertTrue(pp.toString().contains("Expires"));
    }
}
