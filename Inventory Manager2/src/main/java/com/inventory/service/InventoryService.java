package com.inventory.service;

import com.inventory.model.Product;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    boolean addProduct(Product product);
    boolean updateProduct(String productId, Optional<Integer> newQty, Optional<Double> newPrice);
    boolean deleteProduct(String productId);
    List<Product> getAllProducts();
    Product searchProduct(String query); // By ID or Name
    void saveInventoryToFile();
    void loadInventoryFromFile();
}
