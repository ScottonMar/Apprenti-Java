package com.shoppingcart.service;

public interface ShoppingServiceInterface {
    void addItem(String name, double price, int quantity);
    void removeItem(String name, int quantity);
    void displayCart();
    double checkout();
}
