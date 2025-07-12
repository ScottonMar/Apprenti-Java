package com.inventory;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import com.inventory.service.InventoryService;
import com.inventory.util.InputUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryService inventory = new InventoryService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("===== Inventory Manager =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Save Inventory to File");
            System.out.println("7. Load Inventory from File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> viewProducts();
                case "3" -> searchProduct();
                case "4" -> updateProduct();
                case "5" -> deleteProduct();
                case "6" -> saveInventory();
                case "7" -> loadInventory();
                case "8" -> {
                    System.out.print("Are you sure you want to exit? (Y/N): ");
                    if (scanner.nextLine().equalsIgnoreCase("Y")) {
                        running = false;
                    }
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
            System.out.println();
        }
    }

    private static void addProduct() {
        System.out.print("Is this a perishable product? (Y/N): ");
        boolean isPerishable = scanner.nextLine().equalsIgnoreCase("Y");

        String id = InputUtil.readNonEmpty("Enter Product ID: ");
        String name = InputUtil.readNonEmpty("Enter Product Name: ");
        int qty = InputUtil.readInt("Enter Quantity: ");
        double price = InputUtil.readDouble("Enter Price: ");

        Product p;

        if (isPerishable) {
            while (true) {
                System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
                String dateInput = scanner.nextLine();
                try {
                    LocalDate expiryDate = LocalDate.parse(dateInput);
                    p = new PerishableProduct(id, name, qty, price, expiryDate);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Try again.");
                }
            }
        } else {
            p = new Product(id, name, qty, price);
        }

        if (inventory.addProduct(p)) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Product ID already exists!");
        }
    }

    private static void viewProducts() {
        System.out.println("===== Inventory List =====");
        for (Product p : inventory.getAllProducts()) {
            p.displayInfo();
        }
    }

    private static void searchProduct() {
        String input = InputUtil.readNonEmpty("Enter Product ID or Name: ");
        Product p = inventory.searchByIdOrName(input);
        if (p != null) {
            System.out.println("Product Found:");
            p.displayInfo();
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void updateProduct() {
        String id = InputUtil.readNonEmpty("Enter Product ID to update: ");
        Product p = inventory.searchByIdOrName(id);
        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Enter new quantity (or press Enter to skip): ");
        String qtyInput = scanner.nextLine();
        Integer newQty = null;
        if (!qtyInput.isBlank()) {
            try {
                newQty = Integer.parseInt(qtyInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Skipping.");
            }
        }

        System.out.print("Enter new price (or press Enter to skip): ");
        String priceInput = scanner.nextLine();
        Double newPrice = null;
        if (!priceInput.isBlank()) {
            try {
                newPrice = Double.parseDouble(priceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Skipping.");
            }
        }

        if (inventory.updateProduct(id, newQty, newPrice)) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Update failed.");
        }
    }

    private static void deleteProduct() {
        String id = InputUtil.readNonEmpty("Enter Product ID to delete: ");
        Product p = inventory.searchByIdOrName(id);
        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this product? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            inventory.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    private static void saveInventory() {
        System.out.println("Saving inventory...");
        inventory.saveInventory();
        System.out.println("Inventory saved successfully!");
    }

    private static void loadInventory() {
        System.out.println("Loading inventory...");
        inventory.loadInventory();
    }
}
