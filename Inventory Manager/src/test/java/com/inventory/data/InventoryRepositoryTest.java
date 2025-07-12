package com.inventory.data;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    private static final String FILE_PATH = "inventory.txt";
    private List<Product> products;
    private InventoryRepository repository;

    @BeforeEach
    void setup() {
        products = new ArrayList<>();
        repository = new InventoryRepository(products);
    }

    @Test
    void testSaveAndLoadProducts() {
        Product p1 = new Product("T1", "Paper", 100, 0.99);
        Product p2 = new PerishableProduct("T2", "Juice", 20, 2.5, LocalDate.of(2025, 10, 1));
        products.add(p1);
        products.add(p2);

        repository.saveToFile();
        products.clear();
        assertEquals(0, products.size());

        repository.loadFromFile();
        assertEquals(2, products.size());
    }

    @AfterEach
    void tearDown() {
        File file = new File(FILE_PATH);
        if (file.exists()) file.delete();
    }
}
