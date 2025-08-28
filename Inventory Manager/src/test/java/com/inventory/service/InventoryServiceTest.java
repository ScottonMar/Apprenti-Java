package com.inventory.service;

import com.inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService service;

    @BeforeEach
    void setUp() {
        service = new InventoryService();
        service.addProduct(new Product("101", "Laptop", 10, 999.99));
    }

    @Test
    void testAddProduct() {
        boolean result = service.addProduct(new Product("102", "Mouse", 20, 25.0));
        assertTrue(result);
    }

    @Test
    void testAddDuplicateProductFails() {
        boolean result = service.addProduct(new Product("101", "Laptop", 5, 850.0));
        assertFalse(result);
    }

    @Test
    void testGetAllProducts() {
        Collection<Product> products = service.getAllProducts();
        assertEquals(1, products.size());
    }

    @Test
    void testSearchProductById() {
        Product product = service.searchProduct("101");
        assertNotNull(product);
        assertEquals("Laptop", product.getProductName());
    }

    @Test
    void testSearchProductByName() {
        Product product = service.searchProduct("Laptop");
        assertNotNull(product);
        assertEquals("101", product.getProductId());
    }

    @Test
    void testUpdateProduct() {
        boolean updated = service.updateProduct("101", 5, 899.99);
        assertTrue(updated);

        Product product = service.searchProduct("101");
        assertEquals(5, product.getQuantity());
        assertEquals(899.99, product.getPrice());
    }

    @Test
    void testUpdateProductInvalidIdFails() {
        boolean updated = service.updateProduct("999", 5, 100.0);
        assertFalse(updated);
    }

    @Test
    void testDeleteProduct() {
        boolean deleted = service.deleteProduct("101");
        assertTrue(deleted);
        assertNull(service.searchProduct("101"));
    }

    @Test
    void testDeleteProductNotFound() {
        boolean deleted = service.deleteProduct("999");
        assertFalse(deleted);
    }
}
