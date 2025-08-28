package com.inventory.data;

import com.inventory.model.Product;
import com.inventory.model.PerishableProduct;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    private InventoryRepository repo;
    private final String testFileName = "inventory.txt"; // default file used

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadNormalProducts() throws IOException {
        List<Product> products = List.of(
                new Product("001", "Chair", 5, 45.0),
                new Product("002", "Table", 3, 89.99)
        );
        repo.saveInventory(products);

        List<Product> loaded = repo.loadInventory();
        assertEquals(2, loaded.size());
        assertEquals("Chair", loaded.get(0).getProductName());
    }

    @Test
    void testSaveAndLoadPerishableProducts() throws IOException {
        List<Product> products = List.of(
                new PerishableProduct("003", "Milk", 10, 2.50, LocalDate.of(2025, 12, 31))
        );
        repo.saveInventory(products);

        List<Product> loaded = repo.loadInventory();
        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof PerishableProduct);
        assertEquals("Milk", loaded.get(0).getProductName());
    }

    @Test
    void testLoadFromNonexistentFileReturnsEmptyList() throws IOException {
        File file = new File(testFileName);
        if (file.exists()) file.delete();

        List<Product> loaded = repo.loadInventory();
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }
}
