package com.inventory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductCreationAndAccessors() {
        Product p = new Product("P1", "Monitor", 5, 150.0);

        assertEquals("P1", p.getProductID());
        assertEquals("Monitor", p.getProductName());
        assertEquals(5, p.getQuantity());
        assertEquals(150.0, p.getPrice());
    }

    @Test
    void testProductSetters() {
        Product p = new Product("P2", "Keyboard", 10, 25.5);
        p.setQuantity(15);
        p.setPrice(30.0);

        assertEquals(15, p.getQuantity());
        assertEquals(30.0, p.getPrice());
    }

    @Test
    void testCSVConversion() {
        Product original = new Product("P3", "Mouse", 12, 10.99);
        String csv = original.toCSV();
        Product copy = Product.fromCSV(csv);

        assertEquals(original.getProductID(), copy.getProductID());
        assertEquals(original.getProductName(), copy.getProductName());
        assertEquals(original.getQuantity(), copy.getQuantity());
        assertEquals(original.getPrice(), copy.getPrice());
    }
}
