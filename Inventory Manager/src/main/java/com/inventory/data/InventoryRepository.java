package com.inventory.data;

import com.inventory.model.Product;
import com.inventory.model.PerishableProduct;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InventoryRepository {
    private static final String FILE_NAME = "inventory.txt";

    public void saveInventory(Collection<Product> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : products) {
                writer.write(product.toFileString());
                writer.newLine();
            }
        }
    }

    public List<Product> loadInventory() throws IOException {
        List<Product> products = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return products;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("P")) {
                    products.add(new PerishableProduct(
                            parts[1], parts[2],
                            Integer.parseInt(parts[3]),
                            Double.parseDouble(parts[4]),
                            LocalDate.parse(parts[5])));
                } else if (parts[0].equals("N")) {
                    products.add(new Product(
                            parts[1], parts[2],
                            Integer.parseInt(parts[3]),
                            Double.parseDouble(parts[4])));
                }
            }
        }
        return products;
    }
}
