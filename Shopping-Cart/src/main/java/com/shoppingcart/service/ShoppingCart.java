package com.shoppingcart.service;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Item;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> cartItems = new HashMap<>();

    public void addItem(Item item, int quantity) {
        String itemName = item.getName().toLowerCase();
        if (cartItems.containsKey(itemName)) {
            cartItems.get(itemName).increaseQuantity(quantity);
        } else {
            cartItems.put(itemName, new CartItem(item, quantity));
        }
    }

    public void removeItem(String name, int quantity) {
        String itemName = name.toLowerCase();
        if (cartItems.containsKey(itemName)) {
            CartItem cartItem = cartItems.get(itemName);
            cartItem.decreaseQuantity(quantity);
            if (cartItem.getQuantity() <= 0) {
                cartItems.remove(itemName);
            }
        }
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            cartItems.values().forEach(System.out::println);
        }
    }

    public double checkout() {
        double total = cartItems.values().stream()
                .mapToDouble(item -> item.getItem().getPrice() * item.getQuantity())
                .sum();
        cartItems.clear();
        return total;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
