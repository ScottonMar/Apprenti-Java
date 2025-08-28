package com.inventory.ui;

import com.inventory.model.PerishableProduct;
import com.inventory.model.Product;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainMenu {

    private final InventoryService service;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public MainMenu(InventoryService service) {
        this.service = service;
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
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

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> viewProducts();
                case "3" -> searchProduct();
                case "4" -> updateProduct();
                case "5" -> deleteProduct();
                case "6" -> saveToFile();
                case "7" -> loadFromFile();
                case "8" -> exit = confirmExit();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addProduct() {
        System.out.println("\n===== Add Product =====");

        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        if (service.searchProduct(id) != null) {
            System.out.println("Product ID already exists!");
            return;
        }

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine().trim();

        int qty = getIntInput("Enter Quantity: ");
        double price = getDoubleInput("Enter Price: ");

        System.out.print("Is this a perishable product? (Y/N): ");
        String perishable = scanner.nextLine().trim();

        Product product;
        if (perishable.equalsIgnoreCase("Y")) {
            System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
            LocalDate expiry = LocalDate.parse(scanner.nextLine().trim());
            product = new PerishableProduct(id, name, qty, price, expiry);
        } else {
            product = new Product(id, name, qty, price);
        }

        if (service.addProduct(product)) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product!");
        }
    }

    private void viewProducts() {
        System.out.println("\n===== Inventory List =====");
        System.out.printf("%-10s | %-20s | %-8s | %-8s\n", "ID", "Name", "Quantity", "Price");
        System.out.println("-------------------------------------------------------------");

        for (Product p : service.getAllProducts()) {
            System.out.println(p);
        }

        pressEnter();
    }

    private void searchProduct() {
        System.out.println("\n===== Search Product =====");
        System.out.print("Enter Product ID or Name: ");
        String query = scanner.nextLine().trim();

        Product p = service.searchProduct(query);
        if (p == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("Product Found:\n" + p);
        }

        pressEnter();
    }

    private void updateProduct() {
        System.out.println("\n===== Update Product =====");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        Product product = service.searchProduct(id);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("Current Details: " + product);
        System.out.print("Enter New Quantity (or press Enter to skip): ");
        String qtyInput = scanner.nextLine().trim();

        System.out.print("Enter New Price (or press Enter to skip): ");
        String priceInput = scanner.nextLine().trim();

        Optional<Integer> newQty = qtyInput.isEmpty() ? Optional.empty() : Optional.of(Integer.parseInt(qtyInput));
        Optional<Double> newPrice = priceInput.isEmpty() ? Optional.empty() : Optional.of(Double.parseDouble(priceInput));

        if (service.updateProduct(id, newQty, newPrice)) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Update failed!");
        }
    }

    private void deleteProduct() {
        System.out.println("\n===== Delete Product =====");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        Product product = service.searchProduct(id);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Are you sure you want to delete this product? (Y/N): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            service.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        }
    }

    private void saveToFile() {
        System.out.println("\n===== Save Inventory =====");
        service.saveInventoryToFile();
        System.out.println("Inventory successfully saved!");
        pressEnter();
    }

    private void loadFromFile() {
        System.out.println("\n===== Load Inventory =====");
        service.loadInventoryFromFile();
        System.out.println("Inventory successfully loaded!");
        pressEnter();
    }

    private boolean confirmExit() {
        System.out.print("Are you sure you want to exit? (Y/N): ");
        return scanner.nextLine().trim().equalsIgnoreCase("Y");
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Try again.");
            }
        }
    }

    private void pressEnter() {
        System.out.print("Press Enter to return to the main menu...");
        scanner.nextLine();
    }
}
