package com.inventory.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PerishableProductTest {

    @Test
    void testPerishableProductFields() {
        LocalDate expiry = LocalDate.of(2025, 12, 31);
        PerishableProduct p = new PerishableProduct("201", "Milk", 3, 3.99, expiry);

        assertEquals("201", p.getProductId());
        assertEquals("Milk", p.getProductName());
        assertEquals(3, p.getQuantity());
        assertEquals(3.99, p.getPrice());
        assertEquals(expiry, p.getExpiryDate());
    }
}
