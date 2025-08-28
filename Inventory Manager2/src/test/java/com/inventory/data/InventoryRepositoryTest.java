package com.inventory.data;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    private final InventoryRepository repo = new InventoryRepository();
    private final String TEST_FILE = "inventory.txt";

    @Test
    void testSaveAndLoadInventory() {
        Product p1 = new Product("401", "USB Hub", 7, 15.00);
        Product p2 = new PerishableProduct("402", "Yogurt", 10, 1.99, LocalDate.now().plusDays(5));
        List<Product> inventory = List.of(p1, p2);

        repo.saveInventory(inventory);
        List<Product> loaded = repo.loadInventory();

        assertFalse(loaded.isEmpty());
        assertEquals(2, loaded.size());
        assertEquals("USB Hub", loaded.get(0).getProductName());
        assertTrue(new File(TEST_FILE).exists());
    }
}
