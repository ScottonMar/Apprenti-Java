package com.inventory;

import com.inventory.model.Product;
import com.inventory.model.PerishableProduct;
import com.inventory.service.InventoryService;
import com.inventory.data.InventoryRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryService inventoryService = new InventoryService();
    private static final InventoryRepository inventoryRepository = new InventoryRepository();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();

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
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("Y")) {
                        running = false;
                        System.out.println("Exiting Inventory Manager...");
                    }
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }

            if (running) {
                System.out.print("Press Enter to return to the main menu...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== Inventory Manager =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Save Inventory to File");
        System.out.println("7. Load Inventory from File");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct() {
        System.out.println("\n===== Add Product =====");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        if (inventoryService.productExists(id)) {
            System.out.println("Error: Product ID already exists!");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine().trim();

        int quantity;
        double price;
        try {
            System.out.print("Enter Quantity: ");
            quantity = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Price: ");
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity or price format.");
            return;
        }

        System.out.print("Is this product perishable? (Y/N): ");
        String perishable = scanner.nextLine().trim();

        Product product;
        if (perishable.equalsIgnoreCase("Y")) {
            try {
                System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                LocalDate expiry = LocalDate.parse(scanner.nextLine().trim());
                product = new PerishableProduct(id, name, quantity, price, expiry);
            } catch (Exception e) {
                System.out.println("Invalid expiry date format.");
                return;
            }
        } else {
            product = new Product(id, name, quantity, price);
        }

        if (inventoryService.addProduct(product)) {
            saveInventory();
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    private static void viewProducts() {
        System.out.println("\n===== Inventory List =====");
        if (inventoryService.getAllProducts().isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            System.out.println("ID | Name | Quantity | Price");
            System.out.println("-----------------------------------------");
            for (Product p : inventoryService.getAllProducts()) {
                System.out.println(p);
            }
        }
    }

    private static void searchProduct() {
        System.out.println("\n===== Search Product =====");
        System.out.print("Enter Product ID or Name: ");
        String input = scanner.nextLine().trim();
        Product found = inventoryService.searchProduct(input);
        if (found != null) {
            System.out.println("Product Found:");
            System.out.println(found);
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void updateProduct() {
        System.out.println("\n===== Update Product =====");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        Product product = inventoryService.searchProduct(id);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("Current Details: " + product);

        System.out.print("Enter New Quantity (or press Enter to skip): ");
        String newQtyStr = scanner.nextLine().trim();

        System.out.print("Enter New Price (or press Enter to skip): ");
        String newPriceStr = scanner.nextLine().trim();

        int newQty = -1;
        double newPrice = -1;

        try {
            if (!newQtyStr.isEmpty()) {
                newQty = Integer.parseInt(newQtyStr);
            }

            if (!newPriceStr.isEmpty()) {
                newPrice = Double.parseDouble(newPriceStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        boolean updated = inventoryService.updateProduct(id, newQty, newPrice);
        if (updated) {
            System.out.println("Product updated!");
            saveInventory();
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private static void deleteProduct() {
        System.out.println("\n===== Delete Product =====");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        Product product = inventoryService.searchProduct(id);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this product? (Y/N): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            if (inventoryService.deleteProduct(id)) {
                System.out.println("Product deleted.");
                saveInventory();
            } else {
                System.out.println("Failed to delete product.");
            }
        }
    }

    private static void saveInventory() {
        System.out.println("\n===== Save Inventory =====");
        try {
            inventoryRepository.saveInventory(inventoryService.getAllProducts());
            System.out.println("Inventory successfully saved to inventory.txt!");
        } catch (Exception e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    private static void loadInventory() {
        System.out.println("\n===== Load Inventory =====");
        try {
            var products = inventoryRepository.loadInventory();
            inventoryService.loadProducts(products);
            System.out.println("Inventory successfully loaded from inventory.txt!");
        } catch (Exception e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}
