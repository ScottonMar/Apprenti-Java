package com.inventory.service;

import com.inventory.data.InventoryRepository;
import com.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final List<Product> products = new ArrayList<>();
    private final InventoryRepository repository = new InventoryRepository(products);

    public boolean addProduct(Product product) {
        if (products.stream().anyMatch(p -> p.getProductID().equalsIgnoreCase(product.getProductID()))) {
            return false;
        }
        products.add(product);
        return true;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product searchByIdOrName(String input) {
        return products.stream()
                .filter(p -> p.getProductID().equalsIgnoreCase(input) || p.getProductName().equalsIgnoreCase(input))
                .findFirst().orElse(null);
    }

    public boolean updateProduct(String productID, Integer newQuantity, Double newPrice) {
        Product product = searchByIdOrName(productID);
        if (product != null) {
            if (newQuantity != null) product.setQuantity(newQuantity);
            if (newPrice != null) product.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(String productID) {
        return products.removeIf(p -> p.getProductID().equalsIgnoreCase(productID));
    }

    public void saveInventory() {
        repository.saveToFile();
    }

    public void loadInventory() {
        repository.loadFromFile();
    }
}
