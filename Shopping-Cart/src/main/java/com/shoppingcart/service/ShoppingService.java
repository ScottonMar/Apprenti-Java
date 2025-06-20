package com.shoppingcart.service;

import com.shoppingcart.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ShoppingService implements ShoppingServiceInterface {
    private final ShoppingCart cart = new ShoppingCart();
    private final List<Item> catalog = new ArrayList<>();

    public ShoppingService() {
        // Optional: Preload catalog with default items
        catalog.add(new Item("Apple", 1.25));
        catalog.add(new Item("Banana", 0.75));
        catalog.add(new Item("Milk", 2.49));
        catalog.add(new Item("Bread", 1.99));
    }

    private Item findItem(String name) {
        return catalog.stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addItem(String name, double price, int quantity) {
        Item item = findItem(name);
        if (item == null) {
            item = new Item(name, price);
            catalog.add(item);
        }
        cart.addItem(item, quantity);
    }

    @Override
    public void removeItem(String name, int quantity) {
        cart.removeItem(name, quantity);
    }

    @Override
    public void displayCart() {
        cart.displayCart();
    }

    @Override
    public double checkout() {
        double total = cart.checkout();
        System.out.printf("Total: $%.2f\n", total);
        return total;
    }
}
