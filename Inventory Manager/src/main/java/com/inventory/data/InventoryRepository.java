package com.inventory.data;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InventoryRepository {
    private final List<Product> products;
    private final String filePath = "inventory.txt";

    public InventoryRepository(List<Product> products) {
        this.products = products;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Product p : products) {
                writer.println(p.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }

    public void loadFromFile() {
        products.clear();
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("No existing inventory file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    products.add(PerishableProduct.fromCSV(line));
                } else {
                    products.add(Product.fromCSV(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data!");
        }
    }
}
