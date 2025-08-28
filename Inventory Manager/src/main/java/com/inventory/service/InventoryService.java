package com.inventory.service;

import com.inventory.model.Product;

import java.util.*;

public class InventoryService {
    private final Map<String, Product> inventory = new HashMap<>();

    public boolean addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) return false;
        inventory.put(product.getProductId(), product);
        return true;
    }

    public Collection<Product> getAllProducts() {
        return inventory.values();
    }

    public Product searchProduct(String query) {
        return inventory.values().stream()
                .filter(p -> p.getProductId().equalsIgnoreCase(query) || p.getProductName().equalsIgnoreCase(query))
                .findFirst().orElse(null);
    }

    public boolean updateProduct(String id, Integer quantity, Double price) {
        Product p = inventory.get(id);
        if (p == null) return false;
        if (quantity != null) p.setQuantity(quantity);
        if (price != null) p.setPrice(price);
        return true;
    }

    public boolean deleteProduct(String id) {
        return inventory.remove(id) != null;
    }

    public Map<String, Product> getInventoryMap() {
        return inventory;
    }

    public void loadFromMap(Map<String, Product> map) {
        inventory.clear();
        inventory.putAll(map);
    }

    public boolean productExists(String id) {
        return false;
    }

    public void loadProducts(List<Product> products) {

    }
}
