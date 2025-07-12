package com.inventory.service;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventory;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
    }

    @Test
    void testAddAndSearchProduct() {
        Product p = new Product("A1", "Chair", 3, 49.99);
        assertTrue(inventory.addProduct(p));

        Product found = inventory.searchByIdOrName("A1");
        assertNotNull(found);
        assertEquals("Chair", found.getProductName());
    }

    @Test
    void testPreventDuplicateProduct() {
        Product p1 = new Product("B1", "Desk", 5, 89.99);
        Product p2 = new Product("B1", "Table", 2, 99.99);

        assertTrue(inventory.addProduct(p1));
        assertFalse(inventory.addProduct(p2)); // Duplicate ID
    }

    @Test
    void testUpdateProduct() {
        Product p = new Product("C1", "Lamp", 10, 15.0);
        inventory.addProduct(p);

        boolean updated = inventory.updateProduct("C1", 20, 18.5);
        assertTrue(updated);

        Product updatedProduct = inventory.searchByIdOrName("C1");
        assertEquals(20, updatedProduct.getQuantity());
        assertEquals(18.5, updatedProduct.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Product p = new Product("D1", "Fan", 6, 25.0);
        inventory.addProduct(p);
        assertTrue(inventory.deleteProduct("D1"));
        assertNull(inventory.searchByIdOrName("D1"));
    }

    @Test
    void testAddPerishableProduct() {
        PerishableProduct pp = new PerishableProduct("PX", "Eggs", 30, 4.5, LocalDate.now().plusDays(10));
        assertTrue(inventory.addProduct(pp));

        Product found = inventory.searchByIdOrName("PX");
        assertNotNull(found);
        assertInstanceOf(PerishableProduct.class, found);
    }
}
