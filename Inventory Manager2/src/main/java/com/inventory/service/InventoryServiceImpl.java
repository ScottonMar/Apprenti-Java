package com.inventory.service;

import com.inventory.data.InventoryRepository;
import com.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final Map<String, Product> inventory = new LinkedHashMap<>();

    @Autowired
    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            return false;
        }
        inventory.put(product.getProductId(), product);
        return true;
    }

    @Override
    public boolean updateProduct(String productId, Optional<Integer> newQty, Optional<Double> newPrice) {
        Product product = inventory.get(productId);
        if (product == null) return false;

        newQty.ifPresent(product::setQuantity);
        newPrice.ifPresent(product::setPrice);
        return true;
    }

    @Override
    public boolean deleteProduct(String productId) {
        return inventory.remove(productId) != null;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public Product searchProduct(String query) {
        return inventory.values().stream()
                .filter(p -> p.getProductId().equalsIgnoreCase(query)
                        || p.getProductName().equalsIgnoreCase(query))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveInventoryToFile() {
        repository.saveInventory(getAllProducts());
    }

    @Override
    public void loadInventoryFromFile() {
        List<Product> loaded = repository.loadInventory();
        inventory.clear();
        for (Product product : loaded) {
            inventory.put(product.getProductId(), product);
        }
    }
}
