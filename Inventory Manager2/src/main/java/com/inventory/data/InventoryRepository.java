package com.inventory.data;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

@Component
public class InventoryRepository {

    private static final String FILE_PATH = "inventory.txt";

    public void saveInventory(List<Product> inventory) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Product product : inventory) {
                if (product instanceof PerishableProduct) {
                    PerishableProduct p = (PerishableProduct) product;
                    writer.write(String.format("PERISHABLE,%s,%s,%d,%.2f,%s\n",
                            p.getProductId(), p.getProductName(), p.getQuantity(),
                            p.getPrice(), p.getExpiryDate()));
                } else {
                    writer.write(String.format("PRODUCT,%s,%s,%d,%.2f\n",
                            product.getProductId(), product.getProductName(),
                            product.getQuantity(), product.getPrice()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving data!");
        }
    }

    public List<Product> loadInventory() {
        List<Product> inventory = new ArrayList<>();

        if (!Files.exists(Paths.get(FILE_PATH))) {
            System.err.println("Inventory file not found.");
            return inventory;
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals("PERISHABLE")) {
                    inventory.add(new PerishableProduct(
                            tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                            Double.parseDouble(tokens[4]), LocalDate.parse(tokens[5])));
                } else if (tokens[0].equals("PRODUCT")) {
                    inventory.add(new Product(
                            tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                            Double.parseDouble(tokens[4])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inventory file.");
        }

        return inventory;
    }
}
