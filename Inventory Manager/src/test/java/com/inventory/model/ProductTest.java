package com.inventory.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product("001", "Laptop", 10, 999.99);
        assertEquals("001", product.getProductId());
        assertEquals("Laptop", product.getProductName());
        assertEquals(10, product.getQuantity());
        assertEquals(999.99, product.getPrice());
    }

    @Test
    void testSetQuantityAndPrice() {
        Product product = new Product("002", "Mouse", 20, 25.50);
        product.setQuantity(15);
        product.setPrice(19.99);

        assertEquals(15, product.getQuantity());
        assertEquals(19.99, product.getPrice());
    }

    @Test
    void testStockManagement() {
        Product product = new Product("003", "Keyboard", 5, 49.99);
        product.increaseStock(10);
        assertEquals(15, product.getQuantity());

        product.decreaseStock(3);
        assertEquals(12, product.getQuantity());
    }

    @Test
    void testToStringFormat() {
        Product product = new Product("004", "Monitor", 8, 150.0);
        String expected = "004 | Monitor | 8 | $150.00";
        assertEquals(expected, product.toString());
    }

    @Test
    void testEquality() {
        Product p1 = new Product("005", "Speaker", 5, 80.0);
        Product p2 = new Product("005", "Speaker", 10, 100.0);
        assertEquals(p1, p2);
    }
}
