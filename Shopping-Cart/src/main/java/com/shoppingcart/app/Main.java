package com.shoppingcart.app;

import com.shoppingcart.service.ShoppingService;
import com.shoppingcart.service.ShoppingServiceInterface;
import com.shoppingcart.util.MenuUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Correct instantiation of the implementing class
        ShoppingServiceInterface service = new ShoppingService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Shopping Inc POS System!");

        while (running) {
            MenuUtil.printMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> service.displayCart();
                case "2" -> {
                    System.out.print("Enter item name to remove: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity to remove: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    service.removeItem(name, qty);
                }
                case "3" -> {
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    service.addItem(name, price, qty);
                }
                case "4" -> service.checkout();
                case "5" -> {
                    System.out.println("Exiting... Have a great day!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}