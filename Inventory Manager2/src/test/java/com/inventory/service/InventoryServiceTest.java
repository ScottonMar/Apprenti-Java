package com.inventory.service;

import com.inventory.data.InventoryRepository;
import com.inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new InventoryServiceImpl(new InventoryRepository());
    }

    @Test
    void testAddAndSearchProduct() {
        Product p = new Product("301", "Tablet", 4, 199.99);
        assertTrue(service.addProduct(p));
        assertEquals("Tablet", service.searchProduct("301").getProductName());
    }

    @Test
    void testUpdateProduct() {
        Product p = new Product("302", "Monitor", 6, 129.99);
        service.addProduct(p);
        service.updateProduct("302", Optional.of(10), Optional.of(99.99));
        Product updated = service.searchProduct("302");

        assertEquals(10, updated.getQuantity());
        assertEquals(99.99, updated.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Product p = new Product("303", "Docking Station", 2, 49.99);
        service.addProduct(p);
        assertTrue(service.deleteProduct("303"));
        assertNull(service.searchProduct("303"));
    }
}
