package com.inventory.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductCreationAndGetters() {
        Product p = new Product("101", "Mouse", 10, 19.99);
        assertEquals("101", p.getProductId());
        assertEquals("Mouse", p.getProductName());
        assertEquals(10, p.getQuantity());
        assertEquals(19.99, p.getPrice());
    }

    @Test
    void testProductSetters() {
        Product p = new Product("102", "Keyboard", 5, 29.99);
        p.setQuantity(7);
        p.setPrice(25.50);
        assertEquals(7, p.getQuantity());
        assertEquals(25.50, p.getPrice());
    }
}
